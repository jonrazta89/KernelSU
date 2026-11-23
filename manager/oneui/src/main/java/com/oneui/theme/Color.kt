package com.oneui.theme

import androidx.compose.ui.graphics.Color

/**
 * Samsung OneUI Material 3 color tokens.
 *
 * Reverse-engineered from **Samsung Phone v16.4.00.42** (`com.samsung.android.dialer`),
 * which ships a semantic token layer (`basic_token_*`) that maps onto the Material 3
 * color roles. These are the resolved hex values.
 *
 * The accent colors (primary / green / red / orange) are identical in light and dark
 * mode — that is intentional Samsung behavior. Only the neutral surfaces and text
 * swap between light and dark.
 */
object OneUIColors {
    // ── Neutral gray scale (13-step) ──────────────────────────────
    val Gray01 = Color(0xFFFCFCFF) // lightest
    val Gray05 = Color(0xFFF3F3F5)
    val Gray10 = Color(0xFFE9E9EC)
    val Gray15 = Color(0xFFE4E4E7)
    val Gray20 = Color(0xFFCCCCCE)
    val Gray30 = Color(0xFFB7B7BB)
    val Gray40 = Color(0xFFA3A3A7)
    val Gray50 = Color(0xFF848487)
    val Gray60 = Color(0xFF636368)
    val Gray70 = Color(0xFF4D4D52)
    val Gray80 = Color(0xFF3A3A3D)
    val Gray90 = Color(0xFF252528)
    val Gray95 = Color(0xFF17171A)
    val Gray100 = Color(0xFF010102) // darkest

    // ── Primary (blue) ────────────────────────────────────────────
    val Primary40 = Color(0xFF598FFF)
    val Primary50 = Color(0xFF387AFF) // brand
    val Primary60 = Color(0xFF2E65D4)

    // ── Functional accents ────────────────────────────────────────
    val Green = Color(0xFF11A85F)
    val Red = Color(0xFFD93E36)
    val Orange = Color(0xFFE65B17)

    // ── Text / surface helpers (light) ────────────────────────────
    val OnPrimary = Color.White
    val BackgroundLight = Gray05
    val SurfaceLight = Gray05
    val SurfaceVariantLight = Gray10
    val OnSurfaceLight = Gray100
    val OnSurfaceVariantLight = Gray60
    val OutlineLight = Gray30
    val OutlineVariantLight = Gray20

    // ── Text / surface helpers (dark) ─────────────────────────────
    val BackgroundDark = Gray100
    val SurfaceDark = Gray100
    val SurfaceVariantDark = Gray80
    val OnSurfaceDark = Gray05
    val OnSurfaceVariantDark = Gray40
    val OutlineDark = Gray60
    val OutlineVariantDark = Gray70
}
