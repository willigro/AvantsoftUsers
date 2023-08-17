package com.avantsoft.components.theme


import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * TODO: move dimens to resources
 * */
@Immutable
data class AppDimensions(
    val paddingSmall: Dp = 4.dp,
    val paddingMedium: Dp = 12.dp,
    val paddingLarge: Dp = 24.dp,
    val divider: Dp = 2.dp,
    val listUserDimens: ListUserDimens = ListUserDimens(),
)

data class ListUserDimens(
    val borderWidth: Dp = 4.dp,
)

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }