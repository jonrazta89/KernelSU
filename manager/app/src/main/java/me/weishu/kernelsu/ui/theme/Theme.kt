package me.weishu.kernelsu.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

enum class ColorMode(val value: Int) {
    SYSTEM(0),
    LIGHT(1),
    DARK(2);

    companion object {
        fun fromValue(value: Int) = entries.find { it.value == value } ?: SYSTEM
    }

    val isSystem: Boolean get() = value == 0
    val isDark: Boolean get() = value == 2
    val isAmoled: Boolean get() = false
}

data class AppSettings(
    val colorMode: ColorMode,
    val keyColor: Int,
)

object ThemeController {
    fun getAppSettings(repo: me.weishu.kernelsu.data.repository.SettingsRepository = me.weishu.kernelsu.data.repository.SettingsRepositoryImpl()): AppSettings {
        val colorModeValue = repo.themeMode
        val colorMode = ColorMode.fromValue(colorModeValue)
        val keyColor = repo.keyColor

        return AppSettings(colorMode, keyColor)
    }
}

@Composable
fun KernelSUTheme(
    appSettings: AppSettings = ThemeController.getAppSettings(),
    content: @Composable () -> Unit
) {
    OneUIKernelSUTheme(
        appSettings = appSettings,
        content = content
    )
}

@Composable
@ReadOnlyComposable
fun isInDarkTheme(): Boolean {
    return when (LocalColorMode.current) {
        1 -> false  // Force light mode
        2 -> true   // Force dark mode
        else -> isSystemInDarkTheme()  // Follow system (0 or default)
    }
}

val LocalColorMode = staticCompositionLocalOf { 0 }

val LocalEnableNavigationBadge = staticCompositionLocalOf { true }

val LocalModuleDescriptionMaxLines = staticCompositionLocalOf { 4 }
