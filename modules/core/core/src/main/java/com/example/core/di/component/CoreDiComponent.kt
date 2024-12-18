package com.example.core.di.component

import com.example.core.di.module.NavigationModule
import com.example.core.di.providers.NavigationProvider
import dagger.Component

@Component(modules = [NavigationModule::class])
interface CoreDiComponent : NavigationProvider {

    companion object {
        fun create(): CoreDiComponent {
            return DaggerCoreDiComponent.builder().build()
        }
    }
}