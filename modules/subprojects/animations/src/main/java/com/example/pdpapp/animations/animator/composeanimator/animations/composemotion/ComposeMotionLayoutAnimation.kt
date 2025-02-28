package com.example.pdpapp.animations.animator.composeanimator.animations.composemotion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
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

private const val FRAME_DURATION = 16L
private const val TRANSITIONS_PAUSE = 300L
private const val PROGRESS_PER_FRAME = 0.016f
private const val MAX_PROGRESS = 1f
private const val MIN_PROGRESS = 0f

@OptIn(ExperimentalMotionApi::class)
class ComposeMotionLayoutAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.MotionLayout
    private var progress by mutableFloatStateOf(0f)
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private var transition by mutableStateOf(Transitions.Default)
    private var isPlaying: Boolean = false

    override fun animate() {
        if (!isPlaying) {
            isPlaying = true
            coroutineScope.launch {
                startTransition(Transitions.Default)
                delay(TRANSITIONS_PAUSE)
                startTransition(Transitions.Reverse)
                isPlaying = false
            }
        }
    }

    private suspend fun startTransition(type: Transitions) {
        progress = 0f
        transition = type
        while (progress <= MAX_PROGRESS) {
            delay(FRAME_DURATION)
            progress += PROGRESS_PER_FRAME
        }
    }


    @Composable
    override fun Inflate() {
        val context = LocalContext.current
        val motionSceneContent = remember {
            context.resources.openRawResource(R.raw.motion_scene)
                .readBytes()
                .decodeToString()
        }

        MotionLayout(
            motionScene = MotionScene(motionSceneContent),
            progress = progress,
            transitionName = transition.value,
            modifier = Modifier
                .fillMaxWidth()
                .height(SystemDimens.size.size200dp)
        ) {
            val backProperties = remember { customProperties(id = MotionObjects.Background.value) }
            val iconProperties = remember { customProperties(id = MotionObjects.Icon.value) }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(SystemDimens.radius.radius16dp))
                    .fillMaxWidth()
                    .layoutId(MotionObjects.Background.value)
                    .background(backProperties.color(ColorProperties.Background.value))
            )

            Icon(
                painter = painterResource(R.drawable.ic_play_circle),
                contentDescription = null,
                tint = iconProperties.color(ColorProperties.IconTint.value),
                modifier = Modifier
                    .layoutId(MotionObjects.Icon.value)
            )

            Box(
                modifier = Modifier
                    .background(
                        SystemColors.Purple40,
                        shape = RoundedCornerShape(SystemDimens.radius.radius8dp)
                    )
                    .padding(all = SystemDimens.padding.padding16dp)
                    .layoutId(MotionObjects.Text.value)
            ) {
                Text(
                    text = stringResource(R.string.animator_object_title),
                    style = SystemFonts.typography.titleLarge
                )
            }
        }
    }
}