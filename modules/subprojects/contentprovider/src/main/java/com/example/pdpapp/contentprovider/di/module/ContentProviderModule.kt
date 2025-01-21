package com.example.pdpapp.contentprovider.di.module

import android.content.Context
import com.example.pdpapp.contentprovider.storage.db.ProductDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ContentProviderModule {

    @Singleton
    @Provides
    fun provideProductsDataBase(context: Context): ProductDataBase {
        return ProductDataBase.init(context)
    }
}