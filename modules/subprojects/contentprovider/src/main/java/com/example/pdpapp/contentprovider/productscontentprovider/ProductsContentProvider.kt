package com.example.pdpapp.contentprovider.productscontentprovider

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.pdpapp.contentprovider.storage.db.ProductDataBase
import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.contentprovider.storage.repository.ProductRepository
import com.example.pdpapp.contentprovider.storage.repository.ProductsRepositoryContract

class ProductsContentProvider : ContentProvider() {

    private lateinit var productsRepository: ProductsRepositoryContract

    companion object {
        const val AUTHORITY: String = "com.example.pdpapp.productsprovider"

        const val URL: String = "content://" + AUTHORITY + "/${ProductDataBase.DB_NAME}"
        val CONTENT_URI: Uri = Uri.parse(URL)
        const val WHOLE_TABLE_CODE = 1
        const val PARTICULAR_ROW_CODE = 2
        val uriMatcher: UriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        fun getDeleteUri(productId: Int): Uri {
            return Uri.parse("$URL/$productId")
        }
    }

    init {
        uriMatcher.addURI(AUTHORITY, ProductDataBase.DB_NAME, WHOLE_TABLE_CODE)
        uriMatcher.addURI(AUTHORITY, ProductDataBase.DB_NAME + "/#", PARTICULAR_ROW_CODE)
    }

    override fun onCreate(): Boolean {
        context?.let { callContext ->
            productsRepository = ProductRepository(
                productsDb = ProductDataBase.init(callContext)
            )
            return true
        }
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor {
        val cursor = when (uriMatcher.match(uri)) {
            WHOLE_TABLE_CODE -> productsRepository.getAllProducts()
            PARTICULAR_ROW_CODE -> productsRepository.getAllProducts()
            else -> null
        }

        if (cursor != null && context != null) {
            cursor.setNotificationUri(context!!.contentResolver, uri)
            return cursor
        } else {
            throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            WHOLE_TABLE_CODE -> "vnd.android.cursor.dir/vnd.$AUTHORITY.${ProductDataBase.DB_NAME}"
            PARTICULAR_ROW_CODE -> "vnd.android.cursor.item/vnd.$AUTHORITY.${ProductDataBase.DB_NAME}"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        return when (uriMatcher.match(uri)) {
            WHOLE_TABLE_CODE -> {
                val product = values?.let { Product.fromContentValues(values) }

                if (product != null) {
                    val id = productsRepository.insertProduct(product)
                    context?.contentResolver?.notifyChange(uri, null)
                    ContentUris.withAppendedId(uri, id)
                } else {
                    throw IllegalArgumentException("Unsupported content values: $values")
                }
            }

            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return when (uriMatcher.match(uri)) {
            PARTICULAR_ROW_CODE -> {
                val count = productsRepository.deleteProductById(ContentUris.parseId(uri))
                context?.contentResolver?.notifyChange(uri, null)
                count
            }

            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return when (uriMatcher.match(uri)) {
            WHOLE_TABLE_CODE -> {
                val product = values?.let { Product.fromContentValues(values) }

                if (product != null) {
                    val count = productsRepository.updateProduct(product)
                    context?.contentResolver?.notifyChange(uri, null)
                    count
                } else {
                    throw IllegalArgumentException("Unsupported content values: $values")
                }
            }

            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }
}