package com.example.adminobattriyola.view.riwayatobat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.widgets.daftarobat.Category
import com.example.adminobattriyola.widgets.riwayatobat.ContentDetail
import com.example.adminobattriyola.widgets.riwayatobat.RiwayatObatContent
import com.example.adminobattriyola.widgets.riwayatobat.RiwayatShimering
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun RiwayatObatScreen(
    viewModel: RiwayatObatViewModel
) {
    val searchValue = remember {
        mutableStateOf("2023")
    }
    viewModel.getAllData(searchValue.value)
    val isRefresh by viewModel.isRefreshing.collectAsState()
    val uiState = viewModel.uiState.collectAsState().value
    val showDetail = remember {
        mutableStateOf(false)
    }
    val currentIndex = remember {
        mutableStateOf<Int?>(null)
    }
    val selectedCategory = remember {
        mutableStateOf(false)
    }
    val currentIndexes = remember {
        mutableStateOf(0)
    }
    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefresh), onRefresh = { viewModel.refresh(searchValue.value) }) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,

            ) { paddingValue ->
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier
                    .padding(paddingValue)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .height(120.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                                .background(MaterialTheme.colors.onPrimary)
                                .height(80.dp)
                        )

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .offset(y = 20.dp)
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = stringResource(R.string.title_riwayat_obat),
                                style = MaterialTheme.typography.h1,
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onSurface
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Surface(
                                color = MaterialTheme.colors.onPrimary,
                                border = BorderStroke(3.dp, MaterialTheme.colors.background),
                                shape = CircleShape,
                                modifier = Modifier
                                    .padding(8.dp),
                                elevation = 0.dp
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(MaterialTheme.colors.onPrimary)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Surface(
                        color = Color.Transparent,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp)
                    ) {
                        Column {
                            LazyRow(content = {
                                itemsIndexed(viewModel.riwayatCategory) { index, item ->
                                    selectedCategory.value =
                                        index ==  currentIndexes.value
                                    Category(
                                        category = item,
                                        boolean = selectedCategory.value,
                                        currentIndex = index,
                                        searchValue = searchValue,
                                        index = {
                                            currentIndexes.value = it
                                        })
                                }
                            })
                            Spacer(modifier = Modifier.height(20.dp))
                            Column(
                                modifier = Modifier
                                    .padding(end = 14.dp)
                            ) {
                                Divider(
                                    color = MaterialTheme.colors.surface.copy(0.2F),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                LazyColumn(content = {
                                    if(uiState.body?.data != null) {
                                        itemsIndexed(uiState.body?.data) { index,item ->
                                            showDetail.value = index == currentIndex.value
                                            RiwayatObatContent(showDetail.value,
                                                bulan = item.bulan!!,
                                                contentDetailTransaksi = {
                                                    item.pengajuan!!.forEach { item ->
                                                        ContentDetail(
                                                            status = item.jenis_transaksi!!,
                                                            jumlah = item.jumlah!!.toString(),
                                                            jenis_obat = item.nomer_transaksi!!,
                                                            nama_obat = "Paramex",
                                                            satuan = "Box",
                                                            tanggal = item.tanggal_transaksi!!)
                                                        Spacer(modifier = Modifier.height(8.dp))
                                                    }
                                                }) {
                                                if (currentIndex.value == index) {
                                                    currentIndex.value = null
                                                } else {
                                                    currentIndex.value = index
                                                }

                                            }
                                            Spacer(modifier = Modifier.height(16.dp))
                                        }
                                    } else {
                                        items(20) {
                                            RiwayatShimering()
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }
                                })
                            }

                        }
                    }
                }
            }
        }
    }

}



