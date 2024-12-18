package com.example.pdpapp.di

import com.example.core.di.component.CoreDiComponent
import com.example.core.di.providers.CoreDependenciesProvider
import com.example.core.di.providers.NavigationProvider
import com.example.pdpapp.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [NavigationProvider::class]
)
interface AppComponent: CoreDependenciesProvider {

    companion object {
        fun create(): AppComponent {
            val coreDiComponent = CoreDiComponent.create()
            return DaggerAppComponent.builder()
                .navigationProvider(coreDiComponent)
                .build()
        }
    }

    fun inject(mainActivity: MainActivity)
}