package com.example.adminobattriyola.widgets.tambahobat

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R

@Composable
fun IconFAQ(showAdviceDialog: MutableState<Boolean>) {
    AdviceDialog(boolean = showAdviceDialog)
    IconButton(onClick = { showAdviceDialog.value = true }) {
        Icon(
            painter = painterResource(id = R.drawable.question_icon),
            contentDescription = null,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .size(16.dp)
        )
    }
}