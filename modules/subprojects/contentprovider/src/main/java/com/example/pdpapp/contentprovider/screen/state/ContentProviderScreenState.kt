package com.example.pdpapp.contentprovider.screen.state

import android.content.ContentResolver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.pdpapp.contentprovider.productscontentprovider.ProductsContentProvider
import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.contentprovider.storage.model.toContentValues
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.screen.state.ScreenState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentProviderScreenState(
    override val appNavigator: AppNavigator,
    private val contentResolver: ContentResolver
) : ScreenState() {

    var products: SnapshotStateList<Product> = SnapshotStateList()
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var isRefreshing: Boolean by mutableStateOf(false)

    override fun init() {
        loadProducts()
    }

    private fun loadProducts() {
        products.clear()
        coroutineScope.launch {
            contentResolver.query(
                ProductsContentProvider.CONTENT_URI,
                emptyArray(),
                null,
                emptyArray(),
                null
            ).use { cursor ->
                cursor?.let {
                    val productsInStorage = mutableListOf<Product>()
                    while (it.moveToNext()) {
                        val product = Product.fromCursor(it)
                        if (product != null) {
                            productsInStorage.add(0, product)
                        }
                    }
                    products.addAll(productsInStorage)
                }
            }
        }
    }

    fun onInsert(product: Product) {
        coroutineScope.launch {
            contentResolver.insert(
                ProductsContentProvider.CONTENT_URI,
                product.toContentValues()
            )
            loadProducts()
        }
    }

    fun onEdit(product: Product) {
        coroutineScope.launch {
            contentResolver.update(
                ProductsContentProvider.CONTENT_URI,
                product.toContentValues(),
                null,
                null
            )
            loadProducts()
        }
    }

    fun onDelete(productId: Int) {
        val deleteUri = ProductsContentProvider.getDeleteUri(productId)
        coroutineScope.launch {
            contentResolver.delete(deleteUri, null, null)
            loadProducts()
        }
    }
}