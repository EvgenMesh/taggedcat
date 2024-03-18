package com.example.cats.di.dagger

import android.content.Context
import com.example.data.api.ApiClient
import com.example.data.api.CatApi
import com.example.data.api.PokemonApi
import com.example.data.db.AppDatabase
import com.example.data.db.CatsDao
import com.example.data.repository.CatRepositoryImpl
import com.example.data.repository.PokemonRepositoryImpl
import com.example.domain.repository.CatRepository
import com.example.domain.repository.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCatsDao(appDatabase: AppDatabase) = appDatabase.catsDao()

    @Singleton
    @Provides
    fun provideCatApi() = CatApi(ApiClient.client)

    @Singleton
    @Provides
    fun provideCatRepository(catsDao: CatsDao, catApi: CatApi) : CatRepository = CatRepositoryImpl(catsDao, catApi)

    @Singleton
    @Provides
    fun providePokemonApi() = PokemonApi(ApiClient.client)

    @Singleton
    @Provides
    fun providePokemonRepository(pokemonApi: PokemonApi) : PokemonRepository = PokemonRepositoryImpl(pokemonApi)
}