package com.example.adminobattriyola.view.pengajuan.statuspengajuan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.widgets.daftarobat.Category
import com.example.adminobattriyola.widgets.statuspengajuan.RiwayatStatusPengajuanContent

@Composable
fun StatusPengajuan(
    navController:NavController
) {
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
                                status = status[index],
                                navController = navController
                            )
                            Spacer(modifier = Modifier.height(14.dp))
                        }
                    })
                }
            }
        }
    }
}



