package com.example.pdpapp.contentprovider.screen.dialog.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.pdpapp.contentprovider.R
import com.example.pdpapp.contentprovider.screen.dialog.InsertResult
import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.core.presentation.dialog.state.DialogState

private const val FREE_PRICE_VALUE = 0.0
private const val MAX_PRICE_VALUE = 5000.0

class InsertProductDialogState(
    override val actionButtonTextRes: Int = R.string.action_insert,
    override val titleRes: Int = R.string.insert_dialog_title,
    private val initialProduct: Product = Product(),
) : DialogState<InsertResult>() {

    var productName: String by mutableStateOf(initialProduct.name)
    private var productPrice: Double by mutableDoubleStateOf(initialProduct.price)
    var stringPrice: String by mutableStateOf("")

    init {
        stringPrice = if (productPrice > FREE_PRICE_VALUE) productPrice.toString() else ""
        updateInsertButtonState()
    }

    fun onChangeProductName(name: String) {
        productName = name
        updateInsertButtonState()
    }

    fun onChangeProductPrice(price: String) {
        val newPrice = price.toDoubleOrNull()
        if (newPrice != null && newPrice >= FREE_PRICE_VALUE || price == "") {
            stringPrice = price
            productPrice = newPrice ?: FREE_PRICE_VALUE
        }
        updateInsertButtonState()
    }

    private fun updateInsertButtonState() {
        isActionButtonEnabled = productPrice in FREE_PRICE_VALUE..MAX_PRICE_VALUE
                && productName.isNotEmpty()
    }

    override fun getResult(): InsertResult {
        return InsertResult(
            product = Product(
                id = initialProduct.id,
                name = productName,
                price = productPrice
            )
        )
    }
}