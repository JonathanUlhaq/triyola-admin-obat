package com.example.adminobattriyola.widgets.daftarobat

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Category(
    category: String,
    boolean: Boolean,
    currentIndex: Int,
    searchValue:MutableState<String> = mutableStateOf(""),
    index: (Int) -> Unit
) {

    val backgroundColor by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground)
    val fontColor by animateColorAsState(
        targetValue = if (boolean) MaterialTheme.colors.onSurface else MaterialTheme.colors.surface.copy(
            0.8F
        )
    )

    IconButton(onClick = {
        index.invoke(currentIndex)
        if (category == "Semua") {
            searchValue.value = ""
        } else {
            searchValue.value = category
        }
    }) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = backgroundColor,
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = category,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 12.dp, start = 20.dp, end = 20.dp),
                style = MaterialTheme.typography.body2,
                color = fontColor
            )
        }
    }

}