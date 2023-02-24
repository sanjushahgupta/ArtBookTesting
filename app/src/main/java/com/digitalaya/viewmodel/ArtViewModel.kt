package com.digitalaya.viewmodel

import android.icu.text.StringSearch
import android.media.Image
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalaya.artbooktesting.roomdb.Art
import com.digitalaya.model.ImageResponse
import com.digitalaya.repo.ArtRepository
import com.digitalaya.repo.ArtRepositoryInterface
import com.digitalaya.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ArtViewModel @Inject constructor(private val repository: ArtRepositoryInterface
) : ViewModel() {

    //Art Fragment
    val artList = repository.getArt()

    //Image API Fragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
        val selectedImageUrl: LiveData<String>
        get() = selectedImage


    //Art Details Fragment

    private var insertArtMsg = MutableLiveData<Resource<Art>>()
    val insertArtMessage : LiveData<Resource<Art>>
    get() = insertArtMsg

    fun resetInsertArtMsg(){
        insertArtMsg = MutableLiveData<Resource<Art>>()

    }

    fun setSelectedImage(url: String){
        selectedImage.postValue(url)
    }

    fun deleteArt(art: Art) = viewModelScope.launch{
        repository.deleteArt(art)
    }

    fun insertArt(art: Art) = viewModelScope.launch{
        repository.insertArt(art)
    }

    fun makeArt(name : String, artistName: String){
        if(name.isEmpty() || artistName.isEmpty()){
            insertArtMsg.postValue(Resource.error("Enter name", null))
            return
        }
        val art = Art(name = name, artistName = artistName, imageUrl = selectedImage.value?:"")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }

    fun searchForImage(searchString: String){

        if(searchString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response  = repository.searchImage(searchString)
            images.value = response
        }
    }


}