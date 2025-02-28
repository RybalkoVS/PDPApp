package com.example.pdpapp.core.animator

interface AnimatorContract {

    var animations: List<AnimationContract>

    fun showTypedAnimation(type: AnimationType)
}