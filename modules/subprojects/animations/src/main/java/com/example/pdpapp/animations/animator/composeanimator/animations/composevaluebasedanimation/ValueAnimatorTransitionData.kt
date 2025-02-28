package com.example.pdpapp.animations.animator.composeanimator.animations.composevaluebasedanimation

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

class ValueAnimatorTransitionData(
    color: State<Color>,
    radius: State<Dp>
) {
    val color by color
    val radius by radius
}