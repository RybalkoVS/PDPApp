package com.example.pdpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.navigator.AppNavigator
import com.example.core.navigation.screen.route.Routes
import com.example.pdpapp.di.component.AppComponent
import com.example.pdpapp.di.screentypealias.TypedScreen
import com.example.pdpapp.ui.theme.PDPAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @set:Inject
    lateinit var screens: Set<TypedScreen>

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppComponent.create().inject(this)

        setContent {
            PDPAppTheme {
                val navController = rememberNavController()
                appNavigator.initNavController(navController)

                NavHost(
                    startDestination = Routes.Root.routeName,
                    navController = navController
                ) {
                    screens.forEach { screen ->
                        composable(screen.route.routeName) {
                            screen.view()
                        }
                    }
                }
            }
        }
    }
}