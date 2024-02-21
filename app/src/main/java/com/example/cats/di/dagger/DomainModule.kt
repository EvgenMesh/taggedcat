package com.example.cats.di.dagger

import com.example.domain.repository.CatRepository
import com.example.domain.usecase.GetCatsUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun getCatsUseCase(catRepository: CatRepository) = GetCatsUseCase(catRepository)
}