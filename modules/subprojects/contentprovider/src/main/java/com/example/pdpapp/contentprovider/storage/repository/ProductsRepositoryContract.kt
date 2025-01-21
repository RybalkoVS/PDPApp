package com.example.pdpapp.contentprovider.storage.repository

import android.database.Cursor
import com.example.pdpapp.contentprovider.storage.model.Product

interface ProductsRepositoryContract {

    fun getAllProducts(): Cursor

    fun insertProduct(product: Product): Long

    fun deleteProductById(productId: Long): Int

    fun updateProduct(product: Product): Int
}