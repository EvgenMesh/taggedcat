package com.example.cats.di.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}