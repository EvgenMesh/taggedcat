package com.example.cats.di

import androidx.room.Room
import com.example.data.api.ApiClient
import com.example.data.api.CatApi
import com.example.data.db.AppDatabase
import com.example.data.db.CatsDao
import com.example.data.db.DB_NAME
import com.example.data.db.MediaTypeConverter
import com.example.data.repository.CatRepositoryImpl
import com.example.domain.repository.CatRepository
import com.example.domain.usecase.GetCatsUseCase
import com.example.main.ui.CatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { CatApi(ApiClient.client) }
    single {
        Room.databaseBuilder(
            get(), AppDatabase::class.java, DB_NAME
        ).addTypeConverter(MediaTypeConverter()).build()
    }
    single<CatsDao> {
        val database = get<AppDatabase>()
        database.catsDao()
    }
    factory<CatRepository> { CatRepositoryImpl(get(), get()) }
    factory { GetCatsUseCase(get()) }
    viewModel { CatViewModel(get()) }
}