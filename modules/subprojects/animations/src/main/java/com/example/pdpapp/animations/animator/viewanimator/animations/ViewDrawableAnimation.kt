package com.example.pdpapp.animations.animator.viewanimator.animations

import android.graphics.drawable.AnimatedVectorDrawable
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.pdpapp.animations.R
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import javax.inject.Inject

class ViewDrawableAnimation @Inject constructor() : AnimationContract {

    override val type: AnimationType = AnimationType.Drawable
    private var animateDrawable = {}

    override fun animate() {
        animateDrawable()
    }

    @Composable
    override fun Inflate() {
        AndroidView(
            modifier = Modifier.size(SystemDimens.size.size150dp),
            factory = { context ->
                val animatedView = ImageView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    setImageDrawable(
                        AppCompatResources.getDrawable(
                            context,
                            R.drawable.animated_vector
                        )
                    )
                    animateDrawable = {
                        (drawable as? AnimatedVectorDrawable)?.start()
                    }
                }
                animatedView
            }
        )
    }
}