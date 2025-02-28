package com.example.pdpapp.animations.animator.composeanimator.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.pdpapp.animations.R
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val LOTTIE_ITERATIONS = 2
private const val LOTTIE_MAX_DURATION = 3000L

class ComposeLottieAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.Lottie
    private var isPlaying: Boolean by mutableStateOf(false)
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun animate() {
        coroutineScope.launch {
            isPlaying = true
            delay(LOTTIE_MAX_DURATION)
            isPlaying = false
        }
    }

    @Composable
    override fun Inflate() {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pdp_lottie))
        val progress by animateLottieCompositionAsState(
            composition,
            isPlaying = isPlaying,
            iterations = LOTTIE_ITERATIONS
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
    }
}