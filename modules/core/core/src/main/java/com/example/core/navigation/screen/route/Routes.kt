package com.example.core.navigation.screen.route

enum class Routes(
    override val routeName: String,
    override val enabled: Boolean = false
) : ScreenRoute {
    Root("Root", true),
    ContentProvider("ContentProvider", true),
    Algorithms("Algorithms"),
    Animations("Animations"),
    CustomView("CustomView")
}