package com.example.pdpapp.contentprovider.screen

import android.content.ContentResolver
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.pdpapp.contentprovider.R
import com.example.pdpapp.contentprovider.screen.dialog.InsertProductDialog
import com.example.pdpapp.contentprovider.screen.state.ContentProviderScreenState
import com.example.pdpapp.contentprovider.storage.model.Product
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract
import com.example.pdpapp.core.presentation.screen.Screen
import com.example.pdpapp.core.presentation.screen.route.Routes
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import javax.inject.Inject

@OptIn(ExperimentalMaterial3Api::class)
class ContentProviderScreen @Inject constructor(
    navigator: AppNavigator,
    contentResolver: ContentResolver,
    override val dialogManager: DialogManagerContract
) : Screen<ContentProviderScreenState>() {

    override val route: ScreenRoute = Routes.ContentProvider
    override val screenState: ContentProviderScreenState =
        ContentProviderScreenState(navigator, contentResolver)

    override val view: @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = SystemDimens.padding.padding16dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.content_provider_title),
                style = SystemFonts.typography.titleLarge
            )

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding16dp))

            ProviderActions()

            ProductList()
        }
    }

    @Composable
    private fun ProviderActions() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(SystemDimens.padding.padding4dp)
        ) {
            Button(
                onClick = {
                    showDialog(
                        InsertProductDialog(
                            onHide = { hideDialog() },
                            onInsert = { result ->
                                screenState.onInsert(result.product)
                            }
                        )
                    )
                }
            ) {
                Text(
                    text = stringResource(R.string.action_insert),
                    style = SystemFonts.typography.bodyLarge
                )
            }
        }
    }

    @Composable
    private fun ProductList() {
        val products = screenState.products
        if (!screenState.isRefreshing && products.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(R.string.empty_products_state),
                    style = SystemFonts.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = SystemDimens.padding.padding8dp,
                        vertical = SystemDimens.padding.padding16dp
                    ),
                verticalArrangement = Arrangement.spacedBy(SystemDimens.padding.padding2dp)
            ) {
                items(products) { product ->
                    ProductItem(item = product)
                }
            }
        }
    }

    @Composable
    private fun ProductItem(item: Product) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    SystemColors.PurpleGrey80,
                    shape = RoundedCornerShape(SystemDimens.radius.radius8dp)
                )
                .padding(horizontal = SystemDimens.padding.padding8dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(SystemDimens.weight.weight50),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.weight(SystemDimens.weight.weight25),
                    text = item.name,
                    style = SystemFonts.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.padding(end = SystemDimens.padding.padding8dp))

                Text(
                    text = item.price.toString(),
                    style = SystemFonts.typography.labelSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Row(
                modifier = Modifier.weight(SystemDimens.weight.weight50),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        showDialog(
                            InsertProductDialog(
                                dialogTitle = R.string.update_dialog_title,
                                actionButtonTitle = R.string.action_update,
                                initialProduct = item,
                                onHide = { hideDialog() },
                                onInsert = {
                                    screenState.onEdit(it.product)
                                }
                            )
                        )
                    },
                    imageVector = Icons.Default.Edit,
                    contentDescription = null
                )

                Spacer(Modifier.padding(end = SystemDimens.padding.padding2dp))

                Icon(
                    modifier = Modifier.clickable {
                        screenState.onDelete(item.id)
                    },
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = SystemColors.Red
                )
            }
        }
    }
}