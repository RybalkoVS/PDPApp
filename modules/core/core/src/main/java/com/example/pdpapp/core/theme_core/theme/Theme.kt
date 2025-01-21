package com.example.pdpapp.core.theme_core.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.pdpapp.core.theme_core.color.SystemColors
import com.example.pdpapp.core.theme_core.font.SystemFonts

private val DarkColorScheme = darkColorScheme(
    primary = SystemColors.Purple80,
    secondary = SystemColors.PurpleGrey80,
    tertiary = SystemColors.Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = SystemColors.Purple40,
    secondary = SystemColors.PurpleGrey40,
    tertiary = SystemColors.Pink40
)

@Composable
fun PDPAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = SystemFonts.typography,
        content = content
    )
}