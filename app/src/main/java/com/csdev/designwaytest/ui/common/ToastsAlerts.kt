package com.csdev.designwaytest.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.csdev.designwaytest.ui.theme.BackgroundWhite70F
import com.csdev.designwaytest.ui.theme.BorderWhite30F
import com.csdev.designwaytest.ui.theme.ColorSuccessGreen
import com.csdev.designwaytest.ui.theme.ColorTextError
import kotlinx.coroutines.delay

object ToastAlerts {

    const val DURATION_VERY_SHORT = 100L
    const val DURATION_SHORT = 2000L
    const val DURATION_LONG = 3000L
    const val DURATION_VERY_LONG = 3500L

    @Composable
    fun Success(
        message: String,
        duration: Long = DURATION_SHORT,
        onDismiss: (Boolean) -> Unit
    ) {
        CustomToast(
            icon = Icons.Rounded.CheckCircle,
            color = ColorSuccessGreen,
            message = message,
            duration = duration
        ) {
            onDismiss(it)
        }
    }

    @Composable
    fun Error(
        message: String,
        duration: Long = DURATION_LONG,
        onDismiss: (Boolean) -> Unit
    ) {
        CustomToast(
            icon = Icons.Rounded.Cancel,
            color = ColorTextError,
            message = message,
            duration = duration
        ) {
            onDismiss(it)
        }
    }

    @Composable
    private fun CustomToast(
        icon: ImageVector,
        color: Color,
        message: String,
        duration: Long,
        onDismiss: (Boolean) -> Unit
    ) {
        var isVisible by remember { mutableStateOf(true) }

        LaunchedEffect(key1 = Unit){
            delay(duration)
            isVisible = false
            onDismiss(false)
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn() + slideInVertically(initialOffsetY = { 5000 }),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { 5000 })
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 40.dp)
                        .background(
                            BackgroundWhite70F,
                            RoundedCornerShape(8.dp)
                        )
                        .border(
                            1.dp,
                            BorderWhite30F,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        icon,
                        null,
                        modifier = Modifier.size(21.dp),
                        tint = color
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = message,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.widthIn(max = 210.dp)
                    )
                }
            }
        }
    }

}