package com.xeariix.composex.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.xeariix.composex.modifiers.backgroundBrushOrColor
import com.xeariix.composex.sizes.AppStyle

/**
 * A stateful Button to show a loading state while processing and whether returns a success animated text with optional icon or an error
 * @param text displays the text on the Button,
 *
 */
@Composable
fun StatefulButton(
    text: String,
    initialText: String,
    modifier: Modifier = Modifier,
    state: ButtonState = ButtonState.Initial,
    successModifier: Modifier = Modifier,
    errorModifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    successImageVector: ImageVector? = null,
    errorImageVector: ImageVector? = null,
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
            when (state) {
                ButtonState.Initial -> Text(
                    text = initialText,
                    color = textColor,
                    fontWeight = fontWeight,
                    fontSize = fontSize,
                )

                ButtonState.Loading -> CircularProgressIndicator()
                ButtonState.Success -> Success(
                    text = text,
                    modifier = successModifier,
                    textColor = textColor,
                    imageVector = successImageVector,
                )

                ButtonState.Error -> Error(
                    text = text,
                    modifier = errorModifier,
                    imageVector = errorImageVector,
                )
            }
        }
    }
}

@Composable
fun Success(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
    imageVector: ImageVector? = null,
    contentDescription: String? = null,
    fontWeight: FontWeight? = null,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    Row(modifier = modifier) {
        if (imageVector != null) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription
            )
        }
        Text(
            text = text,
            color = textColor,
            fontSize = fontSize,
            fontWeight = fontWeight,
        )
    }
}

@Composable
fun Error(
    text: String,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
) {
    Text(text = "Error")
}

enum class ButtonStyle {
    Filled,
    Outlined,
}

enum class ButtonState {
    Initial,
    Loading,
    Success,
    Error,
}

@Preview
@Composable
fun StatefulButtonPreview() {
    StatefulButton(
        text = "Test",
        initialText = "Tab to process",
        state = ButtonState.Initial,
    )
}
