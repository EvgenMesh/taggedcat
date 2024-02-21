package com.example.cats.di.dagger

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.data.api.ApiClient
import com.example.data.api.CatApi
import com.example.data.db.AppDatabase
import com.example.data.db.CatsDao
import com.example.data.db.DB_NAME
import com.example.data.db.MediaTypeConverter
import com.example.data.repository.CatRepositoryImpl
import com.example.domain.repository.CatRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Application) = Room.databaseBuilder(
        context, AppDatabase::class.java, DB_NAME
    ).addTypeConverter(MediaTypeConverter()).build()

    @Singleton
    @Provides
    fun provideCatsDao(appDatabase: AppDatabase) = appDatabase.catsDao()

    @Singleton
    @Provides
    fun provideCatApi() = CatApi(ApiClient.client)

    @Provides
    fun provideCatRepository(catsDao: CatsDao, catApi: CatApi) : CatRepository = CatRepositoryImpl(catsDao, catApi)
}