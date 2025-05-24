package com.xeariix.composex.modifiers

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Applies a background to the composable using either a [Brush] or a [Color].
 *
 * - If [brush] is not null, the background is painted using the [Brush].
 * - Otherwise, if [color] is specified (not [Color.Unspecified]), the background is painted using the [Color].
 * - If neither [brush] nor a valid [color] is provided, no background is applied.
 *
 * This modifier allows flexible background styling by prioritizing the brush over the color.
 *
 * @param brush Optional [Brush] used to paint the background.
 * @param color Optional [Color] used to paint the background if [brush] is null or not provided.
 *              Defaults to [Color.Unspecified], which means no color is applied if brush is null.
 * @return The modified [Modifier] with the applied background.
 */
fun Modifier.backgroundBrushOrColor(
    brush: Brush? = null,
    color: Color = Color.Unspecified,
): Modifier = when {
    brush != null -> this.background(brush)
    color != Color.Unspecified -> this.background(color)
    else -> this
}
