package com.example.pdpapp.core.animator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable

@Stable
interface AnimationContract {

    val type: AnimationType

    fun animate()

    @Composable
    fun Inflate()
}