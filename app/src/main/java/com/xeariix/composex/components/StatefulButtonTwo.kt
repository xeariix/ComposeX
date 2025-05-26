package com.xeariix.composex.components
/*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xeariix.composex.ButtonState
import com.xeariix.composex.modifiers.backgroundBrushOrColor
import com.xeariix.composex.sizes.AppStyle

/**
 * A stateful Button to show a loading state while processing and whether returns a success animated text with optional icon or an error
 * @param text displays the text on the Button,
 *
 */
@Composable
fun StatefulButtonTwo(
    state: ButtonState, // TODO: Adjust for reusability
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    contentAlignment: Alignment = Alignment.Center,
    buttonMinHeight: Dp = ButtonDefaults.MinHeight,
    buttonMinWidth: Dp = ButtonDefaults.MinWidth,
    onClickLabel: String? = null,
    role: Role? = null,
    propagateMinConstraints: Boolean = false,
    verticalPadding: Dp = AppStyle.Paddings.Small,
    shape: Shape = ButtonDefaults.shape,
    buttonStyle: ButtonStyle = ButtonStyle.Filled,
    color: Color = MaterialTheme.colorScheme.onSurface,
    brush: Brush? = null,
    shadowElevation: Dp = 0.dp,
    tonalElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
    loadingContent: @Composable () -> Unit = { CircularProgressIndicator() },
    initialContent: @Composable () -> Unit = {},
    errorContent: @Composable RowScope.() -> Unit,
    successContent: @Composable RowScope.() -> Unit,
) {
    Surface(
        modifier = modifier
            .defaultMinSize(
                minWidth = buttonMinWidth,
                minHeight = buttonMinHeight,
            )
            .clickable(
                enabled = !isLoading && state != ButtonState.Success,
                onClickLabel = onClickLabel,
                role = role,
                onClick = onClick,
            ),
        shape = shape,
        color = color,
        border = border,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    ) {
        Box(
            modifier = if (buttonStyle == ButtonStyle.Filled) {
                Modifier
                    .backgroundBrushOrColor(
                        brush = brush,
                        color = color,
                    )
            } else {
                Modifier
            }
                .padding(vertical = verticalPadding),
            contentAlignment = contentAlignment,
            propagateMinConstraints = propagateMinConstraints,
        ) {
            Row(horizontalArrangement = horizontalArrangement) {
                when (state) {
                    is com.xeariix.composex.UiState.ButtonState.Initial -> initialContent()
                    is com.xeariix.composex.UiState.ButtonState.Loading -> loadingContent()
                    is com.xeariix.composex.UiState.ButtonState.Success -> successContent()
                    is com.xeariix.composex.UiState.ButtonState.Error -> errorContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun StatefulButtonTwoPreview() {
    StatefulButton(
        text = "Test",
        initialText = "Tab to process",
        state = ButtonState.Loading,
    )
}

*/