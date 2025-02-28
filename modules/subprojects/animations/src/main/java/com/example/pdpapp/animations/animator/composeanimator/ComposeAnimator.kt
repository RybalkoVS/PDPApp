package com.example.pdpapp.animations.animator.composeanimator

import com.example.pdpapp.animations.di.animationstypealias.Animations
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.animator.AnimatorContract
import javax.inject.Inject
import javax.inject.Named

class ComposeAnimator @Inject constructor(
    @Named("compose") composeAnimations: Animations
) : AnimatorContract {

    override var animations: List<AnimationContract> = listOf()

    init {
        animations = composeAnimations.toList()
    }

    override fun showTypedAnimation(type: AnimationType) {
        animations.firstOrNull { it.type == type }?.animate()
    }
}