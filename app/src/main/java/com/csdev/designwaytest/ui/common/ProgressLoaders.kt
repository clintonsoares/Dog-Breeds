package com.csdev.designwaytest.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.csdev.designwaytest.ui.theme.ColorBlack80F

@Composable
fun IndeterminateProgressLoader(
    isLoading: Boolean,
    message: String
) {
    AnimatedVisibility (
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = ColorBlack80F
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 4.dp
                    )
                    Text(
                        text = "$message...",
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .offset(x = 4.dp),
                        color = Color.White
                    )
                }
            }
        }
    }
}