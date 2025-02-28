package com.example.pdpapp.animations.animator.viewanimator.animations

import android.content.Context
import android.graphics.Typeface
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.children
import androidx.core.view.setPadding
import com.example.pdpapp.animations.R
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import javax.inject.Inject

class ViewViewAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.View
    private var rotationAnim: Animation? = null
    private var playAnim = {}

    override fun animate() {
        playAnim()
    }

    @Composable
    override fun Inflate() {
        AndroidView(
            factory = { context ->
                val animatedView = configureAnimatedView(context)
                rotationAnim =
                    AnimationUtils.loadAnimation(context, R.anim.sample_view_anim)
                playAnim = {
                    animatedView.children.firstOrNull()?.startAnimation(rotationAnim)
                }
                animatedView
            }
        )
    }

    private fun configureAnimatedView(context: Context): ViewGroup {
        //Layout container needed to properly play animation in compose hierarchy
        //returning raw view makes behavior unexpected because such kind of animations not working
        //with actual view object
        return FrameLayout(context).apply {
            addView(Button(context).apply {
                text = context.getString(R.string.animator_object_title)
                isAllCaps = false
                typeface = Typeface.DEFAULT
                textSize = SystemFonts.typography.titleLarge.fontSize.value
                setBackgroundColor(SystemColors.Purple40.toArgb())
                setPadding(
                    (SystemDimens.padding.padding16dp.value * context.resources.displayMetrics.density).toInt()
                )
            })
        }
    }
}