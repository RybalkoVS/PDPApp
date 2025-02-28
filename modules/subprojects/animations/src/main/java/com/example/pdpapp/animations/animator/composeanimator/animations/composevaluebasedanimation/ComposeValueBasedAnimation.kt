package com.example.pdpapp.animations.animator.composeanimator.animations.composevaluebasedanimation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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

class ComposeValueBasedAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.ComposeValueBased
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var animatorObjectState: ValueAnimatorState by mutableStateOf(
        ValueAnimatorState.SquaredUncolored
    )

    override fun animate() {
        coroutineScope.launch {
            animatorObjectState = ValueAnimatorState.RoundedColored
            delay(ANIMATION_DURATION + ANIMATION_PAUSE)
            animatorObjectState = ValueAnimatorState.SquaredUncolored
        }
    }

    @Composable
    override fun Inflate() {
        val transition = valueAnimatorTransition(animatorObjectState)

        Box(
            modifier = Modifier
                .background(
                    color = transition.color,
                    shape = RoundedCornerShape(transition.radius)
                )
                .padding(all = SystemDimens.padding.padding16dp)
        ) {
            Text(
                text = stringResource(R.string.animator_object_title),
                style = SystemFonts.typography.titleLarge,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    private fun valueAnimatorTransition(valueAnimatorState: ValueAnimatorState): ValueAnimatorTransitionData {
        val transition = updateTransition(valueAnimatorState)

        val shape = transition.animateDp(
            transitionSpec = { tween(ANIMATION_DURATION) }
        ) { state ->
            when (state) {
                ValueAnimatorState.SquaredUncolored -> SystemDimens.radius.radius0dp
                ValueAnimatorState.RoundedColored -> SystemDimens.radius.radius16dp
            }
        }
        val color = transition.animateColor(
            transitionSpec = { tween(ANIMATION_DURATION) }
        ) { state ->
            when (state) {
                ValueAnimatorState.SquaredUncolored -> SystemColors.Purple40
                ValueAnimatorState.RoundedColored -> SystemColors.Red
            }
        }
        return remember(transition) { ValueAnimatorTransitionData(color, shape) }
    }
}