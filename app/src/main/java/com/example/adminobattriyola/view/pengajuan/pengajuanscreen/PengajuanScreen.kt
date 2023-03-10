package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.widgets.tambahobat.*

@Composable
fun PengajuanScreen(
    distributor: DistributorViewModel,
    obat: PengajuanObatViewModel
) {
    val uiState = distributor.uiState.collectAsState().value
    val joinState = obat.uiState.collectAsState().value
    val showDialog = remember {
        mutableStateOf(false)
    }
    val addForm = remember {
        mutableStateOf(false)
    }
    obat.getAllData()
    if (uiState.isNotEmpty()) {
        UpdateDialogDistributor(
            model = distributor,
            id = uiState.first().id,
            type = uiState.first().jenis_pengajuan,
            name = uiState.first().distributor,
            address = uiState.first().alamat,
            boolean = showDialog
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            Text(
                text = "Form Alamat",
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier
                    .padding(start = 14.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))
            AnimatedVisibility(visible = uiState.isNotEmpty()) {
                Column {
                    LazyColumn(content = {
                        item {
                            Surface(
                                color = MaterialTheme.colors.onBackground,
                                contentColor = MaterialTheme.colors.onPrimary,
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .clickable { showDialog.value = true }
                            ) {

                                if (uiState.isNotEmpty()) {
                                    Column(
                                        modifier = Modifier
                                            .padding(14.dp)
                                    ) {

                                        PreviewForm(
                                            icon = R.drawable.obat_type,
                                            label = uiState.first().jenis_pengajuan
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        PreviewForm(
                                            icon = R.drawable.distributor_icon,
                                            label = uiState.first().distributor
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        PreviewForm(
                                            icon = R.drawable.address_icon,
                                            label = uiState.first().alamat
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentWidth(Alignment.End)
                                        ) {
                                            ButtonClickSecond(
                                                backgroundColor = MaterialTheme.colors.error,
                                                contentColor = MaterialTheme.colors.onSurface,
                                                text = "Hapus"
                                            ) {
                                                distributor.deleteAll(
                                                    Distributor(
                                                        id = uiState.first().id,
                                                        distributor = uiState.first().distributor,
                                                        jenis_pengajuan = uiState.first().jenis_pengajuan,
                                                        alamat = uiState.first().alamat
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(12.dp))
                        }
                        if (joinState.isNotEmpty()) {
                            itemsIndexed(joinState.first().list) {
                                index, item ->
                                Text(
                                    text = "Nama: " + item.namaObat,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Jumlah: " + item.jumlahObat,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Distributor: " + joinState.first().distributor.distributor,
                                    color = Color.Black
                                )
                                Log.d("PENCETA: ",item.jumlahObat)
                                Log.d("PENCETB: ",joinState.first().distributor.distributor)
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(16.dp))
                            AnimatedVisibility(
                                visible = addForm.value,
                                enter = expandVertically(tween(700), expandFrom = Alignment.Top),
                                exit = shrinkVertically(tween(700), shrinkTowards = Alignment.Top)
                            ) {
                                FormAddObatPengajuan(
                                    obat,
                                    boolean = false,
                                    add = {
                                        if (obat.obatType.value.isNotEmpty() && obat.obatName.value.isNotEmpty() && obat.obatQuantity.value.isNotEmpty()) {
                                            obat.insertData(
                                                PengajuanObat(
                                                    jenisObat = obat.obatType.value,
                                                    namaObat = obat.obatName.value,
                                                    jumlahObat = obat.obatQuantity.value,
                                                    satuanObat = obat.unitType.value,
                                                    distributorId = uiState.first().id
                                                )
                                            )
                                            Log.d("PENCET: ","KEPENCET")
                                            addForm.value = false
                                            obat.obatType.value = ""
                                            obat.obatName.value = ""
                                            obat.obatQuantity.value = ""
                                        }
                                    }) {
                                    addForm.value = false
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            AnimatedVisibility(
                                visible = !addForm.value,
                                enter = expandVertically(tween(700), expandFrom = Alignment.Top),
                                exit = shrinkVertically(tween(700), shrinkTowards = Alignment.Top)
                            ) {
                                ButtonClick(
                                    backgroundColor = MaterialTheme.colors.onBackground,
                                    contentColor = MaterialTheme.colors.primary,
                                    text = "Tambah Form Obat +",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                ) {
                                    addForm.value = true
                                }
                            }
                        }
                    })

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            AnimatedVisibility(visible = uiState.isEmpty()) {
                FormPengajuanDistributor(
                    distributor,
                    add = {
                        if (distributor.distributorName.value.isNotEmpty() && distributor.distributorAddress.value.isNotEmpty() && distributor.pengajuanType.value.isNotEmpty()) {
                            distributor.insertData(
                                Distributor(
                                    distributor = distributor.distributorName.value,
                                    jenis_pengajuan = distributor.pengajuanType.value,
                                    alamat = distributor.distributorAddress.value
                                )
                            )
                            distributor.pengajuanType.value = ""
                            distributor.distributorName.value = ""
                            distributor.distributorAddress.value = ""

                        }
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
