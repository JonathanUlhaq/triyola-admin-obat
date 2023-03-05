package com.example.adminobattriyola.widgets.daftarobat

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ObatCategory(
    category: String,
    boolean: Boolean,
    currentIndex: Int,
    index: (Int) -> Unit
) {

    val backgroundColor by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground)
    val fontColor by animateColorAsState(
        targetValue = if (boolean) MaterialTheme.colors.onSurface else MaterialTheme.colors.surface.copy(
            0.8F
        )
    )

    IconButton(onClick = { index.invoke(currentIndex) }) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = backgroundColor,
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = category,
                modifier = Modifier
                    .padding(8.dp),
                style = MaterialTheme.typography.body2,
                color = fontColor
            )
        }
    }

}