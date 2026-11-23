package me.weishu.kernelsu.ui.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * Returns true to indicate content is ready.
 * Simplified from Miuix version that tracked navigation transitions.
 */
@Composable
fun rememberContentReady(): Boolean {
    return true
}
