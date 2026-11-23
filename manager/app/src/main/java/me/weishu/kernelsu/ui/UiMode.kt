package me.weishu.kernelsu.ui

import androidx.compose.runtime.staticCompositionLocalOf

enum class UiMode(val value: String) {
    OneUI("oneui");

    companion object {
        fun fromValue(value: String): UiMode = OneUI

        val DEFAULT_VALUE = OneUI.value
    }
}

val LocalUiMode = staticCompositionLocalOf { UiMode.OneUI }
