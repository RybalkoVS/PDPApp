package com.example.pdpapp.contentprovider.di.component

import com.example.pdpapp.contentprovider.di.module.ContentProviderModule
import com.example.pdpapp.contentprovider.di.module.ProductsRepositoryModule
import com.example.pdpapp.core.di.providers.AndroidComponentsProvider
import dagger.Component

@Component(
    modules = [ContentProviderModule::class, ProductsRepositoryModule::class],
    dependencies = [AndroidComponentsProvider::class]
)
interface ContentProviderComponent {

    companion object {
        fun create(contextProvider: AndroidComponentsProvider): ContentProviderComponent {
            return DaggerContentProviderComponent.builder()
                .androidComponentsProvider(contextProvider)
                .build()
        }
    }
}