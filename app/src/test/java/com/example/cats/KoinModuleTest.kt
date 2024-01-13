@file:OptIn(KoinExperimentalAPI::class)

package com.example.cats

import com.example.cats.di.mainModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KoinModuleTest : KoinTest{
    @Test
    fun addition_isCorrect() {
        mainModule.verify(extraTypes = listOf(io.ktor.client.HttpClient::class, kotlinx.coroutines.CoroutineDispatcher::class))
    }
}