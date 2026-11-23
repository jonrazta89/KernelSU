package me.weishu.kernelsu.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun rememberBlurBackdrop(enableBlur: Boolean): Any? {
    return null
}

@Composable
fun BlurredBar(
    backdrop: Any?,
    blurActive: Boolean = true,
    content: @Composable () -> Unit,
) {
    Box(modifier = Modifier) {
        content()
    }
}
