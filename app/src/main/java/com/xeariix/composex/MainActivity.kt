package com.xeariix.composex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.xeariix.composex.components.ButtonState
import com.xeariix.composex.components.ButtonStyle
import com.xeariix.composex.components.StatefulButton
import com.xeariix.composex.sizes.AppStyle
import com.xeariix.composex.ui.theme.ComposeXTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeXTheme {
                var state: ButtonState by remember { mutableStateOf(ButtonState.Initial) }

                suspend fun performTask(): Int {
                    state = ButtonState.Loading
                    delay(2000L)  // Simulating a long-running task
                    state = ButtonState.Success
                    return 0
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                        .padding(all = AppStyle.Paddings.Medium),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(
                        modifier = Modifier.background(Color.Transparent),
                        verticalArrangement = Arrangement.spacedBy(AppStyle.Paddings.Medium),
                    ) {
                        StatefulButton(
                            text = "Success",
                            initialText = "Tab to process",
                            state = state,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(AppStyle.Paddings.Medium),
                            buttonMinHeight = AppStyle.Buttons.Normal,
                            buttonStyle = ButtonStyle.Filled,
                            onClick = {
                                lifecycleScope.launch {
                                    performTask()
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
