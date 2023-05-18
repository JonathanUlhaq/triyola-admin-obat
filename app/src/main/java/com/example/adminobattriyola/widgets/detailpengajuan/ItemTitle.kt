package com.example.adminobattriyola.widgets.detailpengajuan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemTitle() {
    Surface(
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.onPrimary,
        contentColor = Color.White,
        modifier = Modifier
            .padding(start = 16.dp,end=16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Reguler",
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp)
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(1.dp)
                .height(20.dp)
                .background(Color.White))
            Text(text = "Kimia Farma",
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp)
            Box(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .width(1.dp)
                .height(20.dp)
                .background(Color.White))
            Text(text = "03/03/2023",
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp)

        }
    }
}