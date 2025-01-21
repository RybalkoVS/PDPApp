package com.example.pdpapp.contentprovider.di.module

import com.example.pdpapp.contentprovider.storage.repository.ProductRepository
import com.example.pdpapp.contentprovider.storage.repository.ProductsRepositoryContract
import dagger.Binds
import dagger.Module

@Module
interface ProductsRepositoryModule {

    @Binds
    fun bindProductsRepository(productRepository: ProductRepository): ProductsRepositoryContract
}