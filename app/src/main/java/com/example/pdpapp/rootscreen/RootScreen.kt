package com.example.pdpapp.rootscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract
import com.example.pdpapp.core.presentation.screen.Screen
import com.example.pdpapp.core.presentation.screen.route.Routes
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import com.example.pdpapp.R
import com.example.pdpapp.rootscreen.state.RootScreenState
import javax.inject.Inject

class RootScreen @Inject constructor(
    navigator: AppNavigator,
    override val dialogManager: DialogManagerContract
) : Screen<RootScreenState>() {
    override val route: ScreenRoute = Routes.Root
    override val screenState: RootScreenState = RootScreenState(appNavigator = navigator)

    override val view: @Composable () -> Unit = {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(
                SystemDimens.padding.padding20dp,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.subprojects_title),
                style = SystemFonts.typography.titleLarge
            )

            screenState.getNonRootEnabledScreens()
                .forEach { route ->
                    Button(
                        onClick = { screenState.navigateToSelectedSubProject(route) }
                    ) {
                        Text(text = route.routeName)
                    }
                }
        }
    }
}