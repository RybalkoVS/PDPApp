package com.example.pdpapp.contentprovider.storage.repository

import android.database.Cursor
import com.example.pdpapp.contentprovider.storage.db.ProductDataBase
import com.example.pdpapp.contentprovider.storage.model.Product
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productsDb: ProductDataBase
) : ProductsRepositoryContract {

    override fun getAllProducts(): Cursor {
        return productsDb.productDao().getAll()
    }

    override fun insertProduct(product: Product): Long {
        return productsDb.productDao().insert(product)
    }

    override fun deleteProductById(productId: Long): Int {
        return productsDb.productDao().deleteById(productId)
    }

    override fun updateProduct(product: Product): Int {
        return productsDb.productDao().update(product)
    }
}