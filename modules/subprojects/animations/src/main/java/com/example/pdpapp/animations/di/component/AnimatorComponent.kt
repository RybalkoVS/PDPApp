package com.example.pdpapp.animations.di.component

import com.example.pdpapp.animations.di.module.AllAnimationsModule
import com.example.pdpapp.animations.di.module.AnimatorModule
import com.example.pdpapp.core.di.providers.AnimatorProvider
import dagger.Component

@Component(
    modules = [AnimatorModule::class, AllAnimationsModule::class]
)
interface AnimatorComponent : AnimatorProvider {

    companion object {
        fun create(): AnimatorComponent {
            return DaggerAnimatorComponent.builder().build()
        }
    }
}