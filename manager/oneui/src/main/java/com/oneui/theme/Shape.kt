package com.oneui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Samsung OneUI Material 3 shape scale.
 *
 * Corner sizes mapped from the appcompat `shapeCornerSize*` attributes
 * (extraSmall → extraExtraLarge).
 */
val OneUIShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp),
)
