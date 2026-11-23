package com.oneui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Samsung OneUI Material 3 light color scheme.
 *
 * Every Material 3 color role is mapped from the OneUI `basic_token_*` values.
 */
private val LightColors = lightColorScheme(
    primary = OneUIColors.Primary50,
    onPrimary = OneUIColors.OnPrimary,
    primaryContainer = OneUIColors.Primary40,
    onPrimaryContainer = OneUIColors.Primary60,

    secondary = OneUIColors.Green,
    onSecondary = OneUIColors.OnPrimary,
    secondaryContainer = OneUIColors.Green.copy(alpha = 0.15f),
    onSecondaryContainer = OneUIColors.Green,

    tertiary = OneUIColors.Orange,
    onTertiary = OneUIColors.OnPrimary,
    tertiaryContainer = OneUIColors.Orange.copy(alpha = 0.15f),
    onTertiaryContainer = OneUIColors.Orange,

    error = OneUIColors.Red,
    onError = OneUIColors.OnPrimary,
    errorContainer = OneUIColors.Red.copy(alpha = 0.15f),
    onErrorContainer = OneUIColors.Red,

    background = OneUIColors.BackgroundLight,
    onBackground = OneUIColors.OnSurfaceLight,

    surface = OneUIColors.SurfaceLight,
    onSurface = OneUIColors.OnSurfaceLight,
    surfaceVariant = OneUIColors.SurfaceVariantLight,
    onSurfaceVariant = OneUIColors.OnSurfaceVariantLight,

    outline = OneUIColors.OutlineLight,
    outlineVariant = OneUIColors.OutlineVariantLight,

    inverseSurface = OneUIColors.Gray90,
    inverseOnSurface = OneUIColors.Gray05,
    inversePrimary = OneUIColors.Primary40,

    surfaceContainerLowest = OneUIColors.Gray01,
    surfaceContainerLow = OneUIColors.Gray05,
    surfaceContainer = OneUIColors.Gray10,
    surfaceContainerHigh = OneUIColors.Gray15,
    surfaceContainerHighest = OneUIColors.Gray20,
)

/**
 * Samsung OneUI Material 3 dark color scheme.
 *
 * Accents stay identical to light mode (Samsung M3 behavior); only neutral
 * surfaces and text swap to their dark tokens.
 */
private val DarkColors = darkColorScheme(
    primary = OneUIColors.Primary50,
    onPrimary = OneUIColors.Gray100,
    primaryContainer = OneUIColors.Primary60,
    onPrimaryContainer = OneUIColors.Primary40,

    secondary = OneUIColors.Green,
    onSecondary = OneUIColors.Gray100,
    secondaryContainer = OneUIColors.Green.copy(alpha = 0.15f),
    onSecondaryContainer = OneUIColors.Green,

    tertiary = OneUIColors.Orange,
    onTertiary = OneUIColors.Gray100,
    tertiaryContainer = OneUIColors.Orange.copy(alpha = 0.15f),
    onTertiaryContainer = OneUIColors.Orange,

    error = OneUIColors.Red,
    onError = OneUIColors.Gray100,
    errorContainer = OneUIColors.Red.copy(alpha = 0.15f),
    onErrorContainer = OneUIColors.Red,

    background = OneUIColors.BackgroundDark,
    onBackground = OneUIColors.OnSurfaceDark,

    surface = OneUIColors.SurfaceDark,
    onSurface = OneUIColors.OnSurfaceDark,
    surfaceVariant = OneUIColors.SurfaceVariantDark,
    onSurfaceVariant = OneUIColors.OnSurfaceVariantDark,

    outline = OneUIColors.OutlineDark,
    outlineVariant = OneUIColors.OutlineVariantDark,

    inverseSurface = OneUIColors.Gray10,
    inverseOnSurface = OneUIColors.Gray90,
    inversePrimary = OneUIColors.Primary60,

    surfaceContainerLowest = OneUIColors.Gray95,
    surfaceContainerLow = OneUIColors.Gray90,
    surfaceContainer = OneUIColors.Gray80,
    surfaceContainerHigh = OneUIColors.Gray70,
    surfaceContainerHighest = OneUIColors.Gray60,
)

/**
 * Samsung OneUI Material 3 theme.
 *
 * @param darkTheme whether to use the dark color scheme. Defaults to the system setting.
 * @param content the composable content to theme.
 */
@Composable
fun OneUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = OneUITypography,
        shapes = OneUIShapes,
        content = content,
    )
}

/**
 * Alias with an explicit color scheme, for consumers that want to override roles.
 */
@Composable
fun OneUITheme(
    colorScheme: androidx.compose.material3.ColorScheme = if (isSystemInDarkTheme()) DarkColors else LightColors,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = OneUITypography,
        shapes = OneUIShapes,
        content = content,
    )
}

// Expose the schemes for advanced use-cases.
val OneUILightColorScheme: androidx.compose.material3.ColorScheme = LightColors
val OneUIDarkColorScheme: androidx.compose.material3.ColorScheme = DarkColors
