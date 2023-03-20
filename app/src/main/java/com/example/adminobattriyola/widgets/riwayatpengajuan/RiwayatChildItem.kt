package com.example.adminobattriyola.widgets.riwayatpengajuan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.components.ButtonClick

@Composable
fun RiwayatChildItem(
    tipePengajuan: String,
    distributor: String,
    tanggal: String,
    onClick:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface.copy(0.2F))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = tipePengajuan,
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.body2
            )
        }
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = distributor,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.body2
        )
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = tanggal,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.body2
        )
    }
    Spacer(modifier = Modifier.height(18.dp))
    ButtonClick(
        backgroundColor = MaterialTheme.colors.secondary.copy(0.6F),
        contentColor = MaterialTheme.colors.onSurface,
        text = "Detail",
        modifier = Modifier.fillMaxWidth(),
        roundedeCorners = 6,
        fontSizes = 14
    ) {
        onClick.invoke()
    }
}