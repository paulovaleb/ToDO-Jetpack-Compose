package com.example.todo.tasks.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6200EE), // Adjusted dark purple color
    secondary = Color(0xFF424242), // Adjusted dark grey color
<<<<<<< HEAD
    tertiary = Color(0xFFC2185B), // Adjusted dark pink color

=======
    tertiary = Color(0xFFC2185B) // Adjusted dark pink color
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF9C27B0), // Adjusted light purple color
    secondary = Color(0xFF757575), // Adjusted light grey color
    tertiary = Color(0xFFF48FB1) // Adjusted light pink color

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ToDOTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
<<<<<<< HEAD
    isBoxOpen: Boolean = false, // New variable indicating if the box is open
=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
<<<<<<< HEAD
    if (isBoxOpen) {
        // Set a different color when the box is open
    }
=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
<<<<<<< HEAD
            window.statusBarColor = if (isBoxOpen) {
                // Set a different color when the box is open
                Color(0xFF000000).copy(alpha = 0.5f).toArgb() // Change this color to your desired shade
            } else {
                colorScheme.primary.toArgb()
            }
=======
            window.statusBarColor = colorScheme.primary.toArgb()
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
<<<<<<< HEAD

=======
>>>>>>> a9def792ec195cb50dacc838ba55fb87f6b09b83
