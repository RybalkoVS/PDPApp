package com.example.pdpapp.rootscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.navigation.navigator.AppNavigator
import com.example.core.navigation.screen.Screen
import com.example.core.navigation.screen.route.Routes
import com.example.core.navigation.screen.route.ScreenRoute
import javax.inject.Inject

class RootScreen @Inject constructor(
    override val navigator: AppNavigator
) : Screen {
    override val route: ScreenRoute = Routes.Root

    override val view: @Composable () -> Unit = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                20.dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Subprojects"
            )

            Routes.entries
                .filter { it != Routes.Root && it.enabled }
                .forEach { route ->
                    Button(
                        onClick = { navigator.navigate(route) }
                    ) {
                        Text(text = route.routeName)
                    }
                }
        }
    }
}