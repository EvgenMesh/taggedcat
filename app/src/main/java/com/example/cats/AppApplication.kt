package com.example.cats

import android.app.Application
import com.example.cats.di.mainModule
import org.koin.core.context.GlobalContext

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            modules(mainModule)
        }
    }
}