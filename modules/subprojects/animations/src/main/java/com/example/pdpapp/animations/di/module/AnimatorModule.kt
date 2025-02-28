package com.example.pdpapp.animations.di.module

import com.example.pdpapp.animations.animator.composeanimator.ComposeAnimator
import com.example.pdpapp.animations.animator.viewanimator.ViewAnimator
import com.example.pdpapp.core.animator.AnimatorContract
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
interface AnimatorModule {

    @Binds
    @Named("compose")
    fun bindComposeAnimator(animator: ComposeAnimator): AnimatorContract

    @Binds
    @Named("view")
    fun bindViewAnimator(animator: ViewAnimator): AnimatorContract
}