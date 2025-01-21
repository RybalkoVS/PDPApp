package com.example.pdpapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.screen.route.Routes
import com.example.pdpapp.core.theme_core.theme.PDPAppTheme
import com.example.pdpapp.di.component.AppComponent
import com.example.pdpapp.di.screentypealias.TypedScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @set:Inject
    lateinit var screens: Set<TypedScreen>

    @Inject
    lateinit var appNavigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        AppComponent.create(this).inject(this)

        setContent {
            PDPAppTheme {
                val navController = rememberNavController()

                LaunchedEffect(Unit) {
                    appNavigator.initNavController(navController)
                }

                NavHost(
                    startDestination = Routes.Root.routeName,
                    navController = navController
                ) {
                    screens.forEach { screen ->
                        composable(screen.route.routeName) {
                            screen.Inflate()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        appNavigator.destroyNavController()
        super.onDestroy()
    }
}