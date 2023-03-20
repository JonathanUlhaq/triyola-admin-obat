package com.example.adminobattriyola.widgets.riwayatobat

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentDetail(status:String) {
    val animateColor by animateColorAsState(targetValue = if (status == "Keluar") MaterialTheme.colors.error else MaterialTheme.colors.onPrimary)
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = animateColor,
        elevation = 0.dp
    ) {
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Hufagrip",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Hufagrip",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface)
            }
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "$status: 4",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Box",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface)
            }
            Text(text = "03/03/2023",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp)
        }
    }
}