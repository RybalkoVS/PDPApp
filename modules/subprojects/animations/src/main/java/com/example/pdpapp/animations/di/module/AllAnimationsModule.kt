package com.example.pdpapp.animations.di.module

import com.example.pdpapp.animations.animator.composeanimator.animations.ComposeLottieAnimation
import com.example.pdpapp.animations.animator.composeanimator.animations.ComposeModifiersAnimation
import com.example.pdpapp.animations.animator.composeanimator.animations.composemotion.ComposeMotionLayoutAnimation
import com.example.pdpapp.animations.animator.composeanimator.animations.ComposeVisibilityContainerAnimation
import com.example.pdpapp.animations.animator.composeanimator.animations.composevaluebasedanimation.ComposeValueBasedAnimation
import com.example.pdpapp.animations.animator.viewanimator.animations.ViewDrawableAnimation
import com.example.pdpapp.animations.animator.viewanimator.animations.ViewPropertyAnimation
import com.example.pdpapp.animations.animator.viewanimator.animations.ViewViewAnimation
import com.example.pdpapp.animations.di.animationstypealias.Animations
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
object AllAnimationsModule {

    @Provides
    @Named("compose")
    fun provideComposeAnimations(
        visibilityContainerAnimation: ComposeVisibilityContainerAnimation,
        modifiersAnimation: ComposeModifiersAnimation,
        valueBasedAnimation: ComposeValueBasedAnimation,
        lottieAnimation: ComposeLottieAnimation,
        motionLayoutAnimation: ComposeMotionLayoutAnimation,
    ): Animations {
        return setOf(
            visibilityContainerAnimation,
            modifiersAnimation,
            valueBasedAnimation,
            lottieAnimation,
            motionLayoutAnimation
        )
    }

    @Provides
    @Named("view")
    fun provideViewAnimations(
        viewAnimation: ViewViewAnimation,
        drawableAnimation: ViewDrawableAnimation,
        propertyAnimation: ViewPropertyAnimation,
    ): Animations {
        return setOf(
            viewAnimation,
            drawableAnimation,
            propertyAnimation
        )
    }
}