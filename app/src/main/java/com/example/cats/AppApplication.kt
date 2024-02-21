package com.example.cats

import android.app.Application
import com.example.cats.di.dagger.ApplicationComponent
import com.example.cats.di.dagger.DaggerApplicationComponent
import com.example.core.di.ComponentProvider
import com.example.core.di.FeatureComponent

class AppApplication : Application(), ComponentProvider {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().application(this).build()
    }

    override fun featureComponent(): FeatureComponent = applicationComponent

}