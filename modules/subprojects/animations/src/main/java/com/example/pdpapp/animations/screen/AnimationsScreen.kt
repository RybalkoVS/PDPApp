package com.example.pdpapp.animations.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.pdpapp.animations.R
import com.example.pdpapp.animations.screen.state.AnimationsScreenState
import com.example.pdpapp.core.animator.AnimationContract
import com.example.pdpapp.core.animator.AnimatorContract
import com.example.pdpapp.core.navigation.navigator.AppNavigator
import com.example.pdpapp.core.presentation.dialogmanager.DialogManagerContract
import com.example.pdpapp.core.presentation.screen.Screen
import com.example.pdpapp.core.presentation.screen.route.Routes
import com.example.pdpapp.core.presentation.screen.route.ScreenRoute
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.dimens.SystemDimens
import com.example.pdpapp.core.theme_core.font.SystemFonts
import javax.inject.Inject
import javax.inject.Named

class AnimationsScreen @Inject constructor(
    appNavigator: AppNavigator,
    @Named("compose") composeAnimator: AnimatorContract,
    @Named("view") viewAnimator: AnimatorContract,
    override val dialogManager: DialogManagerContract,
) : Screen<AnimationsScreenState>() {

    override val route: ScreenRoute = Routes.Animations
    override val screenState: AnimationsScreenState = AnimationsScreenState(
        appNavigator = appNavigator,
        composeAnimator = composeAnimator,
        viewAnimator = viewAnimator
    )

    override val view: @Composable () -> Unit = {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = SystemDimens.padding.padding16dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.animations_title),
                style = SystemFonts.typography.titleLarge
            )

            HorizontalDivider(
                color = SystemColors.PurpleGrey80,
                modifier = Modifier.padding(bottom = SystemDimens.padding.padding8dp)
            )

            AnimatorBox(
                title = R.string.compose_animator_title,
                animations = screenState.getComposeAnimations()
            )

            HorizontalDivider(
                color = SystemColors.PurpleGrey80,
                modifier = Modifier.padding(bottom = SystemDimens.padding.padding8dp)
            )

            AnimatorBox(
                title = R.string.view_animator_title,
                animations = screenState.getViewAnimations()
            )
        }
    }

    @Composable
    private fun AnimatorBox(
        @StringRes title: Int,
        animations: List<AnimationContract>
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(title),
                style = SystemFonts.typography.titleLarge
            )

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding8dp))

            animations.forEach { animation ->
                AnimationBox(animation = animation)
            }
        }
    }

    @Composable
    private fun AnimationBox(animation: AnimationContract) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = SystemDimens.size.size150dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { screenState.startAnimation(animation) }
            ) {
                Text(
                    text = stringResource(R.string.start_animation_title, animation.type.name),
                    style = SystemFonts.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.padding(top = SystemDimens.padding.padding20dp))

            animation.Inflate()
        }
    }
}