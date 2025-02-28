package com.example.pdpapp.core.animator

enum class AnimationType(val isComposable: Boolean = false) {
    View,
    Property,
    Drawable,
    Lottie(true),
    MotionLayout(true),
    ComposeValueBased(true),
    ComposeModifiers(true),
    ComposeVisibilityContainer(true)
}