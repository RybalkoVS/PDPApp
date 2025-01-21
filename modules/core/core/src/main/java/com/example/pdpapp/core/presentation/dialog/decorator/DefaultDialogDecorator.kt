package com.example.pdpapp.core.presentation.dialog.decorator

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.pdpapp.core.presentation.dialog.DialogResultContract
import com.example.pdpapp.core.presentation.dialog.state.DialogState
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts

@Composable
fun <RESULT : DialogResultContract, STATE : DialogState<RESULT>> DefaultDialogDecorator(
    state: STATE,
    onClose: () -> Unit = {},
    onActionClick: (RESULT?) -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(SystemColors.TransparentBlack25)
            .clickable {
                onClose()
            }, contentAlignment = Alignment.Center
    ) {
        BackHandler {
            onClose()
        }

        Column(
            modifier = Modifier
                .padding(
                    vertical = SystemDimens.padding.padding20dp,
                    horizontal = SystemDimens.padding.padding16dp
                )
                .shadow(
                    elevation = SystemDimens.size.size2dp,
                    shape = RoundedCornerShape(size = SystemDimens.radius.radius8dp)
                )
                .clip(RoundedCornerShape(size = SystemDimens.radius.radius8dp))
                .background(SystemColors.White)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DialogTitle(
                text = stringResource(state.titleRes),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = SystemDimens.padding.padding8dp)
            )

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding16dp))

            content()

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding16dp))

            DialogActionButton(
                text = stringResource(state.actionButtonTextRes),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { onActionClick(state.getResult()) },
                enabled = state.isActionButtonEnabled
            )

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding16dp))
        }
    }
}

@Composable
fun DialogTitle(modifier: Modifier = Modifier, text: String) {
    Text(
        text = text,
        style = SystemFonts.typography.titleLarge,
        modifier = modifier,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun DialogActionButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick, enabled = enabled) {
        Text(
            text = text,
            style = SystemFonts.typography.titleLarge,
            modifier = modifier,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}