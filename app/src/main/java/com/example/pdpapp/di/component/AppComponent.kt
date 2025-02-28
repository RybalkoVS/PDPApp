package com.example.pdpapp.di.component

import androidx.activity.ComponentActivity
import com.example.pdpapp.MainActivity
import com.example.pdpapp.animations.di.component.AnimatorComponent
import com.example.pdpapp.contentprovider.di.component.ContentProviderComponent
import com.example.pdpapp.core.di.component.CoreDiComponent
import com.example.pdpapp.core.di.providers.AndroidComponentsProvider
import com.example.pdpapp.core.di.providers.AnimatorProvider
import com.example.pdpapp.core.di.providers.CoreDependenciesProvider
import com.example.pdpapp.core.di.providers.DialogsProvider
import com.example.pdpapp.core.di.providers.NavigationProvider
import com.example.pdpapp.di.module.ScreensModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        NavigationProvider::class,
        AndroidComponentsProvider::class,
        ContentProviderComponent::class,
        DialogsProvider::class,
        AnimatorProvider::class
    ],
    modules = [ScreensModule::class]
)
abstract class AppComponent : CoreDependenciesProvider {

    companion object {
        fun create(activity: ComponentActivity): AppComponent {
            val coreDiComponent = CoreDiComponent.create(activity)

            return DaggerAppComponent.builder()
                .contentProviderComponent(ContentProviderComponent.create(coreDiComponent))
                .androidComponentsProvider(coreDiComponent)
                .navigationProvider(coreDiComponent)
                .dialogsProvider(coreDiComponent)
                .animatorProvider(AnimatorComponent.create())
                .build()
        }
    }

    abstract fun inject(mainActivity: MainActivity)
}