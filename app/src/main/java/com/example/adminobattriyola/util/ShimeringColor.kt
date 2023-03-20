package com.example.adminobattriyola.util

import androidx.compose.ui.graphics.Color

fun shimeringColor(): List<Color> {
    val ShimmerColorShades = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )
    return ShimmerColorShades
}