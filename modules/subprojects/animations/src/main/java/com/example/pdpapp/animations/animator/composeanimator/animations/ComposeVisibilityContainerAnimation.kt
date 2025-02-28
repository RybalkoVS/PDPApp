package com.example.pdpapp.animations.animator.composeanimator.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
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

private const val VISIBILITY_ANIMATION_OFFSET = 300
private const val ANIMATION_DURATION = 1500
private const val ANIMATION_PAUSE = 100L

class ComposeVisibilityContainerAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.ComposeVisibilityContainer

    private var isVisible: Boolean by mutableStateOf(true)
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun animate() {
        coroutineScope.launch {
            isVisible = false
            delay(ANIMATION_DURATION + ANIMATION_PAUSE)
            isVisible = true
        }
    }


    @Composable
    override fun Inflate() {
        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(
                tween(ANIMATION_DURATION)
            ) + slideIn(
                tween(ANIMATION_DURATION)
            ) { IntOffset(VISIBILITY_ANIMATION_OFFSET, 0) },
            exit = fadeOut(
                tween(ANIMATION_DURATION)
            ) + slideOut(
                tween(ANIMATION_DURATION)
            ) { IntOffset(-VISIBILITY_ANIMATION_OFFSET, 0) }
        ) {
            Box(
                modifier = Modifier
                    .background(
                        SystemColors.Purple40,
                        shape = RoundedCornerShape(SystemDimens.radius.radius8dp)
                    )
                    .padding(all = SystemDimens.padding.padding16dp)
            ) {
                Text(
                    text = stringResource(R.string.animator_object_title),
                    style = SystemFonts.typography.titleLarge
                )
            }
        }
    }
}