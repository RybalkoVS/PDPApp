package com.example.contentprovider.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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

class ContentProviderScreen @Inject constructor(
    override val navigator: AppNavigator
) : Screen {
    override val route: ScreenRoute = Routes.ContentProvider

    override val view: @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Content Provider"
            )
        }
    }
}