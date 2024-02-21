package com.example.cats.di.dagger

import android.app.Application
import com.example.main.di.CatsDepsProvider
import com.example.main.di.CatsModule
import com.example.main.ui.CatFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, DomainModule::class, CatsModule::class])
interface ApplicationComponent : CatsDepsProvider {
    override fun injectCatsFragment(catFragment: CatFragment)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}

