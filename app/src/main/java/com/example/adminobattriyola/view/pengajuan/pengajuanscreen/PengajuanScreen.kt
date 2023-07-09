package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.widgets.pengajuan.UpdateDialogDistributor
import com.example.adminobattriyola.widgets.tambahobat.*
import kotlinx.coroutines.runBlocking

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PengajuanScreen(
    distributor: DistributorViewModel,
    obat: PengajuanObatViewModel,
    event:() -> Unit
) {
    val uiState = distributor.uiState.collectAsState().value
    val joinState = obat.uiState.collectAsState().value
    val showDialog = remember {
        mutableStateOf(false)
    }
    val addForm = remember {
        mutableStateOf(false)
    }
    val deleteKonfirmP = remember {
        mutableStateOf(false)
    }
    val deleteKonfirmO = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val isErrorObat = remember {
        mutableStateOf(false)
    }

    val isLoading = remember {
        mutableStateOf(false)
    }
    val saveDialogShow = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    SaveConfirmDialog(
        title = "Konfirmasi Pengajuan Pembelian Obat",
        desc = "Apakah anda yakin ingin mengajukan pembelian obat dari data - data tersebut ?",
        boolean = saveDialogShow) {
        runBlocking {
            isLoading.value = true
            obat.addDistributor(
                uiState.first().jenis_pengajuan,
                uiState.first().distributor,
                uiState.first().alamat,
                isError
            ) {respones ->
                joinState.first().list.forEach {
                        item ->
                    obat.addPengajuanObat(
                        jenis_obat = item.jenisObat,
                        nama_obat = item.namaObat,
                        dosis_obat = item.dosis,
                        jumlah_obat = item.jumlahObat.toInt(),
                        satuan_obat = item.satuanObat,
                        distributor_id = respones.data.id,
                        isError
                    )
                }.let {
                    isLoading.value = false
                    if (!isError.value) {
                        Toast.makeText(context,"Data berhasil diajukan",Toast.LENGTH_SHORT).show()
                        obat.deleteAll()
                        distributor.deleteAll(
                            Distributor(
                                uiState.first().id,
                                uiState.first().distributor,
                                uiState.first().jenis_pengajuan,
                                uiState.first().alamat
                            )
                        )
                        event.invoke()

                    } else {
                        Toast.makeText(context,"Kesalahn Server, data gagal diajukan, coba lagi",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    LoadingDialog(boolean = isLoading)
    CustomDialog(
        boolean = deleteKonfirmP,
        cancel = { deleteKonfirmP.value = false },
        dismiss = { deleteKonfirmP.value = false }) {
        distributor.deleteAll(
            Distributor(
                id = uiState.first().id,
                distributor = uiState.first().distributor,
                jenis_pengajuan = uiState.first().jenis_pengajuan,
                alamat = uiState.first().alamat
            )
        )
        deleteKonfirmP.value = false
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

    Scaffold() {
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

                                if (uiState.isNotEmpty()) {
                                    Column(
                                        Modifier
                                    ) {
//                                            Text(
//                                                "Jenis Pengajuan",
//                                                style = MaterialTheme.typography.h1,
//                                                color = MaterialTheme.colors.onPrimary,
//                                                fontSize = 12.sp
//                                            )
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Surface(
                                            shape = RoundedCornerShape(8.dp),
                                            elevation = 2.dp,
                                            color = MaterialTheme.colors.onPrimary,
                                            onClick = {
                                                showDialog.value = true
                                            }
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(
                                                        start = 14.dp,
                                                        end = 14.dp,
                                                        top = 8.dp,
                                                        bottom = 8.dp
                                                    ),
                                                horizontalArrangement = Arrangement.SpaceBetween,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = uiState.first().jenis_pengajuan,
                                                    style = MaterialTheme.typography.body1,
                                                    fontSize = 12.sp,
                                                    color = Color.White
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .width(1.dp)
                                                        .height(20.dp)
                                                        .background(Color.White)
                                                )
                                                Text(
                                                    text = uiState.first().distributor,
                                                    style = MaterialTheme.typography.body1,
                                                    fontSize = 12.sp,
                                                    color = Color.White
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .width(1.dp)
                                                        .height(20.dp)
                                                        .background(Color.White)
                                                )
                                                Text(
                                                    text = if (uiState.first().alamat.length > 11) uiState.first().alamat.take(
                                                        7
                                                    ) + " ...." else uiState.first().alamat,
                                                    style = MaterialTheme.typography.body1,
                                                    fontSize = 12.sp,
                                                    color = Color.White
                                                )
                                                Box(
                                                    modifier = Modifier
                                                        .clip(RoundedCornerShape(8.dp))
                                                        .width(1.dp)
                                                        .height(20.dp)
                                                        .background(Color.White)
                                                )
                                                Surface(
                                                    shape = RoundedCornerShape(6.dp),
                                                    color = Color.Transparent,
                                                    contentColor = Color.White,
                                                    onClick = {
                                                        deleteKonfirmP.value = true
                                                    }
                                                ) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.trash_icon),
                                                        contentDescription = null,
                                                        modifier = Modifier
                                                            .padding(10.dp)
                                                            .size(14.dp)
                                                    )
                                                }
                                            }
                                        }

//                                            PreviewForm(
//                                                icon = R.drawable.obat_type,
//                                                label = uiState.first().jenis_pengajuan
//                                            )
//                                            Spacer(modifier = Modifier.height(16.dp))
//                                            Text("Distributor",
//                                                style = MaterialTheme.typography.h1,
//                                                color = MaterialTheme.colors.onPrimary,
//                                                fontSize = 12.sp)
//                                            Spacer(modifier = Modifier.height(10.dp))
//                                            PreviewForm(
//                                                icon = R.drawable.distributor_icon,
//                                                label = uiState.first().distributor
//                                            )
//                                            Spacer(modifier = Modifier.height(16.dp))
//                                            Text("Alamat Distributor",
//                                                style = MaterialTheme.typography.h1,
//                                                color = MaterialTheme.colors.onPrimary,
//                                                fontSize = 12.sp)
//                                            Spacer(modifier = Modifier.height(10.dp))
//                                            PreviewForm(
//                                                icon = R.drawable.address_icon,
//                                                label = uiState.first().alamat
//                                            )
//                                            Spacer(modifier = Modifier.height(16.dp))
//                                            Box(
//                                                modifier = Modifier
//                                                    .fillMaxWidth()
//                                                    .wrapContentWidth(Alignment.End)
//                                            ) {
//                                                Spacer(modifier = Modifier.width(14.dp))
//                                                ButtonClickSecond(
//                                                    backgroundColor = MaterialTheme.colors.error,
//                                                    contentColor = MaterialTheme.colors.onSurface,
//                                                    text = stringResource(R.string.hapus)
//                                                ) {
//                                                    distributor.deleteAll(
//                                                        Distributor(
//                                                            id = uiState.first().id,
//                                                            distributor = uiState.first().distributor,
//                                                            jenis_pengajuan = uiState.first().jenis_pengajuan,
//                                                            alamat = uiState.first().alamat
//                                                        )
//                                                    )
//                                                }
//
//                                            }
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
                                itemsIndexed(joinState.first().list) { _, item ->
                                    CustomDialog(
                                        boolean = deleteKonfirmO,
                                        cancel = { deleteKonfirmO.value = false },
                                        dismiss = { deleteKonfirmO.value = false }) {
                                        obat.deleteById(
                                            PengajuanObat(
                                                id = item.id,
                                                jenisObat = item.jenisObat,
                                                namaObat = item.namaObat,
                                                jumlahObat = item.jumlahObat,
                                                satuanObat = item.satuanObat,
                                                distributorId = item.distributorId
                                            )
                                        )
                                        deleteKonfirmO.value = false
                                    }
                                    ItemObatPengajuan(
                                        value = item,
                                        model = obat
                                    ) {
                                        deleteKonfirmO.value = true
                                    }
                                }
                            }
                            item {
                                Spacer(modifier = Modifier.height(16.dp))
                                AnimatedVisibility(
                                    visible = addForm.value,
                                    enter = expandVertically(
                                        tween(700),
                                        expandFrom = Alignment.Top
                                    ),
                                    exit = shrinkVertically(
                                        tween(700),
                                        shrinkTowards = Alignment.Top
                                    )
                                ) {
                                    FormAddObatPengajuan(
                                        obat,
                                        boolean = isErrorObat.value,
                                        add = {
                                            if (obat.obatType.value.isNotEmpty() && obat.obatName.value.isNotEmpty() && obat.obatQuantity.value.isNotEmpty() && obat.dosis.value.isNotEmpty()&& obat.unitType.value.isNotEmpty()) {
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
                                            } else {
                                                isErrorObat.value = true
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
                                    enter = expandVertically(
                                        tween(700),
                                        expandFrom = Alignment.Top
                                    ),
                                    exit = shrinkVertically(
                                        tween(700),
                                        shrinkTowards = Alignment.Top
                                    )
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
                                Spacer(modifier = Modifier.height(18.dp))
                                AnimatedVisibility(visible = joinState.isNotEmpty() && joinState.first().list.isNotEmpty() && uiState.isNotEmpty()) {
                                    ButtonClick(
                                        backgroundColor = MaterialTheme.colors.primary,
                                        contentColor = MaterialTheme.colors.onSurface,
                                        text = stringResource(id = R.string.ajukan),
                                        modifier = Modifier
                                            .padding(
                                                start = 14.dp,
                                                end = 14.dp,
                                                top = 20.dp,
                                                bottom = 20.dp
                                            )
                                            .fillMaxWidth()
                                    ) {
                                        saveDialogShow.value = true

                                    }
                                }
                            }
                        })


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
                                isError,
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

                                    } else {
                                        isError.value = true
                                    }
                                })
                        }
                    })

                }
            }
        }
    }
}
