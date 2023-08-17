package com.avantsoft.components.ui.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun ProgressScreen(modifier: Modifier, isLoadingState: MutableState<Boolean>) {
    if (isLoadingState.value) {
        ProgressScreen(modifier)
    }
}

@Composable
fun ProgressScreen(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = .4f))
            .pointerInput(Unit) {
                detectTapGestures { }
            }
            .pointerInput(Unit) {
                detectTransformGestures { _, _, _, _ -> }
            }, // TODO move to theme
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp), // TODO move to theme
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}