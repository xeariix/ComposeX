package com.xeariix.composex.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xeariix.composex.sizes.AppStyle

/**
 * A highly customizable icon chip component that displays a label and optionally an icon when selected.
 *
 * This chip can be used in filters, selection groups, or settings where visual feedback is required upon interaction.
 * The appearance of the chip dynamically changes based on its clicked state, including background, text, and icon color.
 *
 * @param text the text displayed inside the chip.
 * @param onClick called when this button is clicked
 * @param modifier optional [Modifier] to be applied to the chip
 * @param imageVector optional [ImageVector] to display as an icon when the chip is selected
 * @param containerColor the background [Color] of the chip in its default (unclicked) state
 * @param iconColor the [Color] applied to the icon when visible
 * @param textColor the [Color] applied to the label text in the default state
 * @param shape the [Shape] defining the chip's outline
 * @param elevation the [CardElevation] controlling the elevation of the chip in various states
 * @param border optional [BorderStroke] to draw around the chip’s container
 * @param isClicked indicates whether the chip is currently selected, triggering visual changes and optional icon visibility
 */
@Composable
fun SelectableIconChip(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    containerColor: Color = MaterialTheme.colorScheme.onTertiary,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    textColor: Color = MaterialTheme.colorScheme.primary,
    shape: Shape = CardDefaults.shape,
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
    isClicked: Boolean = false,
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = if (!isClicked) containerColor else MaterialTheme.colorScheme.primary,
        animationSpec = tween(durationMillis = 300),
        label = "ContainerColorAnimation",
    )

    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = animatedContainerColor),
        elevation = elevation,
        border = border,
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 15.dp,
                    vertical = 6.dp,
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (imageVector != null) {
                AnimatedContent(
                    targetState = isClicked,
                    transitionSpec = {
                        (fadeIn() + scaleIn()).togetherWith(fadeOut() + scaleOut())
                    },
                    label = "IconAnimation",
                ) { targetState ->
                    if (targetState) {
                        Icon(
                            imageVector = imageVector,
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .padding(
                                    top = AppStyle.Paddings.Empty,
                                    bottom = AppStyle.Paddings.Empty,
                                    end = AppStyle.Paddings.Small,
                                ),
                            tint = if (!isClicked) iconColor else Color.White,
                        )
                    }
                }
            }

            Text(
                text = text,
                fontSize = 19.sp,
                style = TextStyle(color = if (!isClicked) textColor else Color.White),
            )
        }
    }
}

@Preview
@Composable
fun IconChipPreview() {
    SelectableIconChip(
        text = "Filter",
        onClick = {},
    )
}

@Preview
@Composable
fun IconChipIsClickedPreview() {
    SelectableIconChip(
        text = "Filter",
        onClick = {},
        imageVector = Icons.Default.Done,
        isClicked = true,
    )
}
