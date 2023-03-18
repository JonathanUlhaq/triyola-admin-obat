package com.example.adminobattriyola.view.pengajuan.statuspengajuan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.widgets.daftarobat.Category

@Composable
fun StatusPengajuan() {
    val listStatus = listOf(
        "Semua",
        "Diproses",
        "Disetujui",
        "Ditolak"
    )

    val tipePengajuan = listOf(
        "Reguler",
        "Psikotropi",
        "Reguler"
    )
    val tanggal = listOf(
        "03/03/2023",
        "03/03/2023",
        "03/03/2023"
    )
    val distributor = listOf(
        "Stark Industries",
        "Wayne Enterprise",
        "Spacec X"
    )
    val alamat = listOf(
        "Ngawi",
        "Nganjuk",
        "Ngonde"
    )
    val status = listOf(
        "Disetujui",
        "Ditolak",
        "Diproses"
    )

    val selected = remember {
        mutableStateOf(false)
    }
    val currentSelected = remember {
        mutableStateOf(0)
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 14.dp),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
            ) {
                LazyRow(content = {
                    itemsIndexed(listStatus) { index, item ->
                        selected.value = index == currentSelected.value
                        Category(category = item,
                            boolean = selected.value,
                            currentIndex = index,
                            index = {
                                currentSelected.value = it
                            })
                    }
                })
                Spacer(modifier = Modifier.height(18.dp))
                Column(
                    modifier = Modifier
                        .padding(end = 14.dp)
                ) {
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.2F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(content = {
                        itemsIndexed(tipePengajuan) { index, item ->
                            RiwayatStatusPengajuanContent(
                                tipePengajuan = item,
                                tanggal = tanggal[index],
                                distributor = distributor[index],
                                alamat = alamat[index],
                                status = status[index]
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                        }
                    })
                }
            }
        }
    }
}

@Composable
fun RiwayatStatusPengajuanContent(
    tipePengajuan: String,
    tanggal: String,
    distributor: String,
    alamat: String,
    status: String
) {
    val namaObat = listOf(
        "Hufagrip",
        "Mixagrip",
        "Paramex",
    )
    val jenisObat = listOf(
        "Sirup",
        "Tablet",
        "Tablet",
    )
    val jumlahObat = listOf(
        3,
        4,
        6,
    )
    val satuanObat = listOf(
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
            for (it in 0..2) {
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
                        text = "Selengkapnya",
                        style = MaterialTheme.typography.caption,
                        fontSize = 10.sp,
                        color = MaterialTheme.colors.onPrimary.copy(0.7F),
                        textDecoration = TextDecoration.Underline
                    )
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

@Composable
fun RiwayatStatusPengajuanObatList(
    namaObat: String,
    jenisObat: String,
    jumlahObat: String,
    satuanObat: String

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