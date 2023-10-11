package com.csdev.designwaytest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import com.csdev.designwaytest.R
import com.csdev.designwaytest.ui.common.IndeterminateProgressLoader
import com.csdev.designwaytest.ui.theme.BackgroundGradient

@Composable
fun BaseComposeScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    loaderMessage : String? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = BackgroundGradient
                )
            )
            .systemBarsPadding()
    ) {
        content()
        IndeterminateProgressLoader(
            isLoading,
            loaderMessage ?: stringResource(id = R.string.loading)
        )
    }
}