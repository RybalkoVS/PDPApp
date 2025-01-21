package com.example.pdpapp.contentprovider.screen.dialog

import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.core.presentation.dialog.DialogResultContract

data class InsertResult(
    val product: Product = Product()
) : DialogResultContract
