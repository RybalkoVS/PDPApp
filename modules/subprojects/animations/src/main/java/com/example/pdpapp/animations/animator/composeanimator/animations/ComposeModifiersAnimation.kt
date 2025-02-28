package com.example.pdpapp.animations.animator.composeanimator.animations

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.example.pdpapp.animations.R
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ANIMATION_DURATION = 2000
private const val ANIMATION_PAUSE = 100L

class ComposeModifiersAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.ComposeModifiers
    private var isExpanded: Boolean by mutableStateOf(false)
    private val startingPadding: Dp = SystemDimens.padding.padding16dp
    private val targetPadding: Dp = SystemDimens.padding.padding32dp
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun animate() {
        coroutineScope.launch {
            isExpanded = true
            delay(ANIMATION_DURATION + ANIMATION_PAUSE)
            isExpanded = false
        }
    }

    @Composable
    override fun Inflate() {
        Box(
            modifier = Modifier
                .background(
                    SystemColors.Purple40,
                    shape = RoundedCornerShape(SystemDimens.radius.radius8dp)
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = ANIMATION_DURATION
                    )
                )
                .padding(all = if (isExpanded) targetPadding else startingPadding),
        ) {
            Text(
                modifier = Modifier.offset(
                    x = if (isExpanded) -startingPadding else SystemDimens.padding.padding0dp,
                    y = if (isExpanded) -startingPadding else SystemDimens.padding.padding0dp
                ),
                text = stringResource(R.string.animator_object_title),
                style = SystemFonts.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}