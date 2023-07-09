package com.example.adminobattriyola.widgets.statuspengajuan

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
import androidx.compose.ui.unit.sp

@Composable
fun RiwayatStatusPengajuanObatList(
    namaObat: String,
    jenisObat: String,
    jumlahObat: String,
    satuanObat: String,
    dosisObat:String

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
                    .size(6.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface.copy(0.5F))
            )
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = namaObat,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.width(50.dp)
            )
        }
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = jenisObat,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.width(50.dp)
        )
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = dosisObat,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.surface,
            modifier = Modifier.width(50.dp)
        )
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
        )
        Row {
            Text(
                text = jumlahObat,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface,
                modifier = Modifier.width(10.dp)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = satuanObat,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.surface,
                fontSize = 10.sp,
                modifier = Modifier.width(24.dp)
            )
        }
    }
}