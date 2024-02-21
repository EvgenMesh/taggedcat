package com.example.main.di

import com.example.core.di.FeatureComponent
import com.example.main.ui.CatFragment

interface CatsDepsProvider : FeatureComponent {
    fun injectCatsFragment(catFragment: CatFragment)
}
