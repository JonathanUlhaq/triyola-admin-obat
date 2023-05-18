package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.widgets.pengajuan.UpdateDialogDistributor
import com.example.adminobattriyola.widgets.tambahobat.FormAddObatPengajuan
import com.example.adminobattriyola.widgets.tambahobat.FormPengajuanDistributor
import com.example.adminobattriyola.widgets.tambahobat.ItemObatPengajuan
import com.example.adminobattriyola.widgets.tambahobat.PreviewForm

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
            type = uiState.first().jenis_pengajuan,
            name = uiState.first().distributor,
            address = uiState.first().alamat,
            boolean = showDialog
        )
    }

    Scaffold(bottomBar = {
        ButtonClickSecond(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onSurface,
            text = stringResource(id = R.string.ajukan),
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp, top = 2.dp, bottom = 2.dp)
                .fillMaxWidth()
        ) {

        }
    }) {
        Surface(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {

                AnimatedVisibility(visible = uiState.isNotEmpty()) {
                    Column {
                        LazyColumn(content = {
                            item {
                                Text(
                                    text = stringResource(R.string.form_alamat),
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier
                                        .padding(start = 14.dp)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
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
                                            Text("Jenis Pengajuan",
                                                style = MaterialTheme.typography.h1,
                                                color = MaterialTheme.colors.onPrimary,
                                                fontSize = 12.sp)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            PreviewForm(
                                                icon = R.drawable.obat_type,
                                                label = uiState.first().jenis_pengajuan
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text("Distributor",
                                                style = MaterialTheme.typography.h1,
                                                color = MaterialTheme.colors.onPrimary,
                                                fontSize = 12.sp)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            PreviewForm(
                                                icon = R.drawable.distributor_icon,
                                                label = uiState.first().distributor
                                            )
                                            Spacer(modifier = Modifier.height(16.dp))
                                            Text("Alamat Distributor",
                                                style = MaterialTheme.typography.h1,
                                                color = MaterialTheme.colors.onPrimary,
                                                fontSize = 12.sp)
                                            Spacer(modifier = Modifier.height(10.dp))
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
                                                Spacer(modifier = Modifier.width(14.dp))
                                                ButtonClickSecond(
                                                    backgroundColor = MaterialTheme.colors.error,
                                                    contentColor = MaterialTheme.colors.onSurface,
                                                    text = stringResource(R.string.hapus)
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
                            item {
                                Text(
                                    text = "Form Obat",
                                    style = MaterialTheme.typography.h2,
                                    color = MaterialTheme.colors.onPrimary,
                                    modifier = Modifier
                                        .padding(start = 14.dp)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                            }
                            if (joinState.isNotEmpty()) {
                                itemsIndexed(joinState.first().list) {
                                        _, item ->
                                    ItemObatPengajuan(value = item,
                                        model = obat ) {
                                        obat.deleteById(PengajuanObat(
                                            id = item.id,
                                            jenisObat = item.jenisObat,
                                            namaObat = item.namaObat,
                                            jumlahObat = item.jumlahObat,
                                            satuanObat = item.satuanObat,
                                            distributorId = item.distributorId
                                        ))
                                    }
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
                                            if (obat.obatType.value.isNotEmpty() && obat.obatName.value.isNotEmpty() && obat.obatQuantity.value.isNotEmpty() && obat.dosis.value.isNotEmpty()) {
                                                obat.insertData(
                                                    PengajuanObat(
                                                        jenisObat = obat.obatType.value,
                                                        namaObat = obat.obatName.value,
                                                        jumlahObat = obat.obatQuantity.value,
                                                        satuanObat = obat.unitType.value,
                                                        distributorId = uiState.first().id,
                                                        dosis = obat.dosis.value
                                                    )
                                                )
                                                addForm.value = false
                                                obat.obatType.value = ""
                                                obat.obatName.value = ""
                                                obat.obatQuantity.value = ""
                                                obat.dosis.value = ""
                                                obat.unitType.value = ""
                                            }
                                        }) {
                                        addForm.value = false
                                        obat.obatType.value = ""
                                        obat.obatName.value = ""
                                        obat.obatQuantity.value = ""
                                        obat.dosis.value = ""
                                        obat.unitType.value = ""
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
                   LazyColumn(content = {
                       item {
                           Text(
                               text = stringResource(R.string.form_alamat),
                               style = MaterialTheme.typography.h2,
                               color = MaterialTheme.colors.onPrimary,
                               modifier = Modifier
                                   .padding(start = 14.dp)
                           )
                           Spacer(modifier = Modifier.height(12.dp))
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
                   })

                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
