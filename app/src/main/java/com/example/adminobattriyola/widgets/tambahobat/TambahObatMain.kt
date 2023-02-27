package com.example.adminobattriyola.widgets.tambahobat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel

@Composable
fun TambahObatMain(
    uiState: List<TambahObatModel>,
    model: TambahObatViewModel,
    addForm: MutableState<Boolean>,
    isError: MutableState<Boolean>
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .background(MaterialTheme.colors.onPrimary)
                .height(30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
        ) {
            ListObat(uiState, model, addForm, isError) { item ->
                model.deleteDatabyId(item)
            }

        }

    }
}