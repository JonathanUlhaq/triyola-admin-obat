package com.example.adminobattriyola.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    title:String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        fontSize = 20.sp,
        color = MaterialTheme.colors.onSurface
    )
}