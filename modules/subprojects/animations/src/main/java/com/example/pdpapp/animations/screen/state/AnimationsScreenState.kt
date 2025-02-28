package com.example.pdpapp.animations.screen.state

import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimatorContract
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.screen.state.ScreenState

class AnimationsScreenState(
    override val appNavigator: AppNavigator,
    private val composeAnimator: AnimatorContract,
    private val viewAnimator: AnimatorContract,
) : ScreenState() {

    fun getComposeAnimations(): List<AnimationContract> {
        return composeAnimator.animations
    }

    fun getViewAnimations(): List<AnimationContract> {
        return viewAnimator.animations
    }

    fun startAnimation(animation: AnimationContract) {
        val type = animation.type

        if (type.isComposable) {
            composeAnimator.showTypedAnimation(type)
        } else {
            viewAnimator.showTypedAnimation(type)
        }
    }
}