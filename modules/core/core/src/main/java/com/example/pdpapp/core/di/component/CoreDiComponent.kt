package com.example.pdpapp.core.di.component

import androidx.activity.ComponentActivity
import com.example.pdpapp.core.di.module.AndroidComponentsModule
import com.example.pdpapp.core.di.module.DialogsModule
import com.example.pdpapp.core.di.module.NavigationModule
import com.example.pdpapp.core.di.providers.AndroidComponentsProvider
import com.example.pdpapp.core.di.providers.DialogsProvider
import com.example.pdpapp.core.di.providers.NavigationProvider
import com.example.pdpapp.core.di.scope.AndroidContextScope
import dagger.Component

@AndroidContextScope
@Component(
    modules = [
        NavigationModule::class,
        AndroidComponentsModule::class,
        DialogsModule::class
    ]
)
interface CoreDiComponent : NavigationProvider, AndroidComponentsProvider, DialogsProvider {

    companion object {
        fun create(activity: ComponentActivity): CoreDiComponent {
            return DaggerCoreDiComponent.builder()
                .androidComponentsModule(AndroidComponentsModule(activity))
                .build()
        }
    }

    @Component.Builder
    interface Builder {

        fun androidComponentsModule(module: AndroidComponentsModule): Builder

        fun build(): CoreDiComponent
    }
}