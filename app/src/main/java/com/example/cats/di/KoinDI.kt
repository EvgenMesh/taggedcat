package com.example.cats.di

import com.example.data.api.ApiClient
import com.example.data.api.CatApi
import com.example.data.repository.CatRepositoryImpl
import com.example.domain.repository.CatRepository
import com.example.domain.usecase.GetCatsUseCase
import com.example.presentation.ui.CatViewModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { CatApi(ApiClient.client) }
    factory<CatRepository> { CatRepositoryImpl(get()) }
    factory { GetCatsUseCase(get()) }
    viewModel { CatViewModule(get()) }
}