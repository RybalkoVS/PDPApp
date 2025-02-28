package com.example.pdpapp.animations.animator.viewanimator

import com.example.pdpapp.animations.di.animationstypealias.Animations
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimationType
import com.example.pdpapp.core.animator.AnimatorContract
import javax.inject.Inject
import javax.inject.Named

class ViewAnimator @Inject constructor(
    @Named("view") viewAnimations: Animations
) : AnimatorContract {

    override var animations: List<AnimationContract> = listOf()

    init {
        animations = viewAnimations.toList()
    }

    override fun showTypedAnimation(type: AnimationType) {
        animations.firstOrNull { it.type == type }?.animate()
    }
}