package com.xeariix.composex.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * A highly customizable icon chip component that displays a label and optionally an icon when selected.
 *
 * This chip can be used in filters, selection groups, or settings where visual feedback is required upon interaction.
 * The appearance of the chip dynamically changes based on its clicked state, including background, text, and icon color.
 *
 * @param onClick called when this chip is clicked
 * @param modifier optional [Modifier] to be applied to the chip
 * @param isClicked indicates whether the chip is currently selected, triggering visual changes and optional icon visibility
 * @param enabled whether the chip is enabled for interaction
 * @param role optional [Role] used for accessibility purposes
 * @param onClickLabel optional label for accessibility services to describe the click action
 * @param shape the [Shape] defining the chip's outline
 * @param containerColor the background [Color] of the chip in its default (unclicked) state
 * @param onClickContainerColor the background [Color] of the chip when it is clicked/selected
 * @param elevation the [CardElevation] controlling the elevation of the chip in various states
 * @param border optional [BorderStroke] to draw around the chip’s container
 * @param icon optional [ImageVector] to display as an icon when the chip is selected
 * @param contentDescription optional description for the icon, used for accessibility
 * @param iconSize the size ([Dp]) of the icon displayed when the chip is selected
 * @param iconColor the [Color] applied to the icon when visible
 * @param content the content to display inside the chip, typically a [Text] label
 */
@Composable
fun SelectableIconChip(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isClicked: Boolean = false,
    enabled: Boolean = true,
    role: Role? = null,
    onClickLabel: String? = null,
    shape: Shape = CardDefaults.shape,
    containerColor: Color = MaterialTheme.colorScheme.onTertiary,
    onClickContainerColor: Color = MaterialTheme.colorScheme.primary,
    elevation: CardElevation = CardDefaults.cardElevation(),
    border: BorderStroke? = null,
    icon: ImageVector? = null,
    contentDescription: String? = null,
    iconSize: Dp = 22.dp,
    iconColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable () -> Unit,
) {
    val animatedContainerColor by animateColorAsState(
        targetValue = if (!isClicked) containerColor else onClickContainerColor,
        animationSpec = tween(durationMillis = 300),
        label = "ContainerColorAnimation",
    )

    Card(
        modifier = modifier
            .clip(CardDefaults.shape)
            .clickable(
                enabled = enabled,
                onClickLabel = onClickLabel,
                role = role,
                onClick = onClick,
            ),
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
            if (icon != null) {
                AnimatedContent(
                    targetState = isClicked,
                    transitionSpec = {
                        slideInHorizontally(
                            animationSpec = tween(500),
                            initialOffsetX = { fullWidth -> fullWidth }
                        ) togetherWith
                                slideOutOfContainer(
                                    towards = AnimatedContentTransitionScope.SlideDirection.Down,
                                )
                    },
                    label = "IconAnimation",
                ) { targetState ->
                    if (targetState) {
                        Icon(
                            imageVector = icon,
                            contentDescription = contentDescription,
                            modifier = Modifier
                                .size(iconSize)
                                .padding(end = 6.dp),
                            tint = iconColor,
                        )
                    }
                }
            }

            content()
        }
    }
}

@Preview
@Composable
fun IconChipPreview() {
    SelectableIconChip(
        onClick = {},
    ) {
        Text(text = "Filter")
    }
}

@Preview
@Composable
fun IconChipIsClickedPreview() {
    SelectableIconChip(
        onClick = {},
        isClicked = true,
        icon = Icons.Default.Done,
        iconColor = Color.White,
    ) {
        Text(text = "Filter")
    }
}
