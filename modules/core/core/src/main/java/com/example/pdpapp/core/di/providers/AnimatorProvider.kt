package com.example.pdpapp.core.di.providers

import com.example.pdpapp.core.animator.AnimatorContract
import javax.inject.Named

interface AnimatorProvider {

    @Named("view")
    fun provideViewAnimator(): AnimatorContract

    @Named("compose")
    fun provideComposeAnimator(): AnimatorContract
}