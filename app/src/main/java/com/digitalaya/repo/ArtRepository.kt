package com.digitalaya.repo

import androidx.lifecycle.LiveData
import com.digitalaya.api.RetrofitAPI
import com.digitalaya.artbooktesting.roomdb.Art
import com.digitalaya.artbooktesting.roomdb.ArtDao
import com.digitalaya.model.ImageResponse
import com.digitalaya.util.Resource
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val retrofitAPI: RetrofitAPI
) : ArtRepositoryInterface {
    override suspend fun insertArt(art: Art) {
       artDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
      artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
   return artDao.getObserveArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)

            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                Resource.error("Error", null)
            }

        } catch (e: java.lang.Exception){
            Resource.error("No data!", null)

        }


    }
}