package com.xeariix.composex.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RemovableChip(
    text: String,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    iconSize: Dp = 20.dp,
    isClicked: Boolean = false,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit,
    paddingStart: Dp = 15.dp,
    paddingTop: Dp = 6.dp,
    paddingEnd: Dp = 10.dp,
    paddingBottom: Dp = 6.dp,
    //paddingHorizontal: Dp = 15.dp,
    //paddingVertical: Dp = 6.dp,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(40.dp))
            .clickable(
                enabled = !isClicked,
                onClickLabel = onClickLabel,
                role = role,
                onClick = onClick,
            )
            .background(color = backgroundColor)
            .padding(
                start = paddingStart,
                top = paddingTop,
                end = paddingEnd,
                bottom = paddingBottom
            ),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                // TODO: Pass missing parameters
            )

            Spacer(modifier = Modifier.width(8.dp))

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
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier
                            .size(iconSize)
                            .clickable { onClick() },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RemovableChipReview() {
    RemovableChip(
        text = "Filter",
        onClick = {},
    )
}