package com.digitalaya.repo

import androidx.lifecycle.LiveData
import com.digitalaya.artbooktesting.roomdb.Art
import com.digitalaya.model.ImageResponse
import com.digitalaya.util.Resource
import retrofit2.Response

interface ArtRepositoryInterface {

    suspend fun insertArt(art: Art)

    suspend fun deleteArt(art: Art)

    fun getArt(): LiveData<List<Art>>

     suspend fun searchImage(imageString: String): Resource<ImageResponse>

}