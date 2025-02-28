package com.example.pdpapp.animations.animator.viewanimator.animations

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.setPadding
import com.example.pdpapp.animations.R
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import javax.inject.Inject

private const val START_OFFSET = 0f
private const val END_OFFSET = 150f
private const val ANIM_DURATION = 2000L

class ViewPropertyAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.Property
    private val horizontalBounceOffsetSequence = floatArrayOf(
        START_OFFSET,
        END_OFFSET,
        START_OFFSET,
        -END_OFFSET,
        START_OFFSET
    )
    private val valueAnimator =
        ValueAnimator.ofFloat(*horizontalBounceOffsetSequence)
            .apply {
                duration = ANIM_DURATION
            }

    override fun animate() {
        valueAnimator.start()
    }

    @Composable
    override fun Inflate() {
        AndroidView(
            factory = { context ->
                val animatedView = configureAnimatedView(context)
                valueAnimator.apply {
                    addUpdateListener { anim ->
                        animatedView.translationX = anim.animatedValue as Float
                    }
                }
                animatedView
            },
        )

        DisposableEffect(Unit) {
            onDispose {
                valueAnimator.removeAllUpdateListeners()
            }
        }
    }

    private fun configureAnimatedView(context: Context): View {
        return Button(context).apply {
            text = context.getString(R.string.animator_object_title)
            isAllCaps = false
            typeface = Typeface.DEFAULT
            textSize = SystemFonts.typography.titleLarge.fontSize.value
            setBackgroundColor(SystemColors.Purple40.toArgb())
            setPadding(
                (SystemDimens.padding.padding16dp.value * context.resources.displayMetrics.density).toInt()
            )
        }
    }
}