package com.example.adminobattriyola.widgets.statuspengajuan

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.view.navigation.AppRoute

@Composable
fun RiwayatStatusPengajuanContent(
    tipePengajuan: String,
    tanggal: String,
    distributor: String,
    alamat: String,
    status: String,
    navController:NavController
) {
    val expand = remember {
        mutableStateOf(false)
    }

    val namaObat = listOf(
        "Hufagrip",
        "Mixagrip",
        "Paramex",
        "Hufagrip",
        "Mixagrip",
        "Paramex",
    )
    val jenisObat = listOf(
        "Sirup",
        "Tablet",
        "Tablet",
        "Sirup",
        "Tablet",
        "Tablet",
    )
    val jumlahObat = listOf(
        3,
        4,
        6,
        3,
        4,
        6,
    )
    val satuanObat = listOf(
        "Box",
        "Pcs",
        "Pcs",
        "Box",
        "Pcs",
        "Pcs",
    )
    Surface(
        color = MaterialTheme.colors.background,
        border = BorderStroke(
            2.dp,
            when (status) {
                "Disetujui" -> MaterialTheme.colors.onPrimary
                "Ditolak" -> MaterialTheme.colors.error
                else -> MaterialTheme.colors.surface.copy(0.5F)
            }
        ),
        elevation = 0.dp,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = tipePengajuan,
                    style = MaterialTheme.typography.h1,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.surface
                )

                Text(
                    text = tanggal,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.surface.copy(0.5F)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = distributor,
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp,
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = alamat,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface.copy(0.5F)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Obat yang diajukan",
                style = MaterialTheme.typography.body1,
                fontSize = 12.sp,
                color = MaterialTheme.colors.surface
            )
            Spacer(modifier = Modifier.height(12.dp))
            for (it in 0..if (expand.value) namaObat.size-1 else 2) {
                RiwayatStatusPengajuanObatList(
                    namaObat = namaObat[it],
                    jenisObat = jenisObat[it],
                    jumlahObat = jumlahObat[it].toString(),
                    satuanObat = satuanObat[it]
                )
                Spacer(modifier = Modifier.height(15.dp))
            }
            if (namaObat.size > 2) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                ) {
                    Text(
                        text = if (expand.value) "Secukupnya" else "Selengkapnya",
                        style = MaterialTheme.typography.caption,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.onPrimary.copy(0.7F),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable {
                            expand.value = !expand.value
                            Log.d("EXPAND VALUE: ",expand.value.toString())
                        }
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            if (status == "Disetujui") {
                ButtonClick(
                    backgroundColor = MaterialTheme.colors.secondary.copy(0.6F),
                    contentColor = MaterialTheme.colors.onSurface,
                    text = "Detail",
                    modifier = Modifier.fillMaxWidth(),
                    roundedeCorners = 6,
                    fontSizes = 14
                ) {
                    navController.navigate(AppRoute.DetailPengajuan.route)
                }
                Spacer(modifier = Modifier.height(6.dp))
            }
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            color = MaterialTheme.colors.surface
                        )
                    ) {
                        append("Status: ")
                    }
                    withStyle(
                        SpanStyle(
                            color =
                            when (status) {
                                "Disetujui" -> MaterialTheme.colors.onPrimary
                                "Ditolak" -> MaterialTheme.colors.error
                                else -> MaterialTheme.colors.surface.copy(0.5F)
                            }

                        )
                    ) {
                        append(status)
                    }
                }, style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        }
    }
}