package com.digitalaya.dependencyinjection

import android.content.Context
import android.icu.number.CompactNotation
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.digitalaya.api.RetrofitAPI
import com.digitalaya.artbooktesting.R
import com.digitalaya.artbooktesting.roomdb.ArtDao
import com.digitalaya.artbooktesting.roomdb.ArtDatabase
import com.digitalaya.repo.ArtRepository
import com.digitalaya.repo.ArtRepositoryInterface
import com.digitalaya.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModule {

    @Provides
   @Singleton
    fun injectRoomDatabase(
        @ApplicationContext context: Context) = Room.databaseBuilder(
        context, ArtDatabase::class.java, "ArtBookDB"
        ).build()

    @Provides
    @Singleton
    fun injectDao (database: ArtDatabase)= database.artDao()

    @Provides
    @Singleton
    fun injectRetrofitAPI(): RetrofitAPI{
        return  Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao: ArtDao, api: RetrofitAPI) = ArtRepository(dao, api) as ArtRepositoryInterface


    @Provides
    @Singleton
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground)

        )
}