package com.example.pdpapp.contentprovider.screen.dialog

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.pdpapp.contentprovider.R
import com.example.pdpapp.contentprovider.screen.dialog.state.InsertProductDialogState
import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.core.presentation.dialog.Dialog
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts

class InsertProductDialog(
    @StringRes dialogTitle: Int = R.string.insert_dialog_title,
    @StringRes actionButtonTitle: Int = R.string.action_insert,
    initialProduct: Product = Product(),
    val onHide: () -> Unit = {},
    val onInsert: (InsertResult) -> Unit = {}
) : Dialog<InsertResult, InsertProductDialogState>() {

    override val dialogState: InsertProductDialogState = InsertProductDialogState(
        titleRes = dialogTitle,
        actionButtonTextRes = actionButtonTitle,
        initialProduct = initialProduct
    )

    override val content: @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SystemDimens.padding.padding16dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = dialogState.productName,
                onValueChange = { dialogState.onChangeProductName(it) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.name_hint),
                        style = SystemFonts.typography.labelLarge,
                        color = SystemColors.HintGrey
                    )
                },
                singleLine = true
            )

            Spacer(Modifier.padding(top = SystemDimens.padding.padding4dp))

            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = dialogState.stringPrice,
                onValueChange = { dialogState.onChangeProductPrice(it) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.price_hint),
                        style = SystemFonts.typography.labelLarge,
                        color = SystemColors.HintGrey
                    )
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }

    override fun onClose() {
        onHide()
    }

    override fun onDialogResult(result: InsertResult?) {
        result?.let {
            onInsert(it)
        }
        onClose()
    }
}