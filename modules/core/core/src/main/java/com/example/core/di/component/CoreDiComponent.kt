package com.example.core.di.component

import com.example.core.di.module.NavigationModule
import com.example.core.di.providers.NavigationProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NavigationModule::class])
interface CoreDiComponent : NavigationProvider {

    companion object {
        fun create(): CoreDiComponent {
            return DaggerCoreDiComponent.builder().build()
        }
    }
}