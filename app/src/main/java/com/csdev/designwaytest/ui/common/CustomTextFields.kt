package com.csdev.designwaytest.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.csdev.designwaytest.ui.theme.Pink40
import com.csdev.designwaytest.ui.theme.Purple40

@Composable
fun CustomTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    hint: String = "",
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    minLines: Int = 1,
    maxLines: Int = 1
) {
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        minLines = minLines,
        maxLines = maxLines,
        singleLine = minLines == 1 && maxLines == 1,
        cursorBrush = Brush.verticalGradient(listOf(Purple40, Pink40)),
        decorationBox = { innerTextField ->
            Box(modifier = modifier) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = LocalContentColor.current.copy(
                            alpha = 0.4f
                        ),
                        fontFamily = textStyle.fontFamily,
                        fontWeight = textStyle.fontWeight,
                        fontSize = textStyle.fontSize,
                        lineHeight = textStyle.lineHeight
                    )
                }
                innerTextField()
            }
        }
    )
}

@Composable
fun CustomPasswordTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    isVisible: Boolean = false,
    isError: Boolean = false,
    visibilityIconSize: Dp = 22.dp,
    onVisibilityChanged: (Boolean) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLines: Int = 1
) {
    val passwordVisualTransformation = if (isVisible) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation('*')
    }
    val visibilityIcon = if (isVisible) {
        Icons.Filled.VisibilityOff
    } else {
        Icons.Filled.Visibility
    }
    BasicTextField(
        value = text,
        onValueChange = onTextChanged,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        textStyle = textStyle,
        maxLines = maxLines,
        visualTransformation = passwordVisualTransformation,
        cursorBrush = Brush.verticalGradient(listOf(Purple40, Pink40)),
        decorationBox = { innerTextField ->
            Box(modifier = modifier) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = LocalContentColor.current.copy(
                            alpha = 0.4f
                        ),
                        fontFamily = textStyle.fontFamily,
                        fontWeight = textStyle.fontWeight,
                        fontSize = textStyle.fontSize,
                        lineHeight = textStyle.lineHeight
                    )
                }
                innerTextField()
                Icon(
                    visibilityIcon,
                    null,
                    tint = LocalContentColor.current.copy(
                        alpha = 0.4f
                    ),
                    modifier = Modifier
                        .size(visibilityIconSize)
                        .clip(CircleShape)
                        .align(Alignment.CenterEnd)
                        .clickable { onVisibilityChanged(!isVisible) }
                        .padding(2.dp)
                )
            }
        }
    )
}