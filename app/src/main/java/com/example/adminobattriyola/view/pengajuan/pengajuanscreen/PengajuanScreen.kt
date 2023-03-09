package com.example.adminobattriyola.view.pengajuan.pengajuanscreen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
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
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat

import com.example.adminobattriyola.widgets.tambahobat.FormPengajuanDistributor
import com.example.adminobattriyola.widgets.tambahobat.PreviewForm
import com.example.adminobattriyola.widgets.tambahobat.UpdateDialogDistributor

@Composable
fun PengajuanScreen(
    distributor: DistributorViewModel,
    obat:PengajuanObatViewModel
) {
    val uiState = distributor.uiState.collectAsState().value
    val joinState = obat.uiState.collectAsState().value
    val showDialog = remember {
        mutableStateOf(false)
    }
    obat.getAllData()
    if (uiState.isNotEmpty()) {
        UpdateDialogDistributor(model = distributor,
            id = uiState.first().id ,
            type = uiState.first().jenis_pengajuan ,
            name = uiState.first().distributor,
            address = uiState.first().alamat ,
            boolean = showDialog ) {
            obat.insertData(PengajuanObat(
                jenisObat = "SADS",
                namaObat = "ASDS",
                jumlahObat = "Asd",
                satuanObat = "asdas",
                distributorId = uiState.first().id
            ))
        }
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
                if(uiState.isNotEmpty()) {

                }
                Column {
                    Surface(
                        color = MaterialTheme.colors.onBackground,
                        contentColor = MaterialTheme.colors.onPrimary,
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .clickable { showDialog.value = true }
                    ) {

                        if(uiState.isNotEmpty()) {
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
                                        contentColor = MaterialTheme.colors.onSurface, text = "Hapus"
                                    ) {
                                       distributor.deleteAll(Distributor(
                                           id = uiState.first().id,
                                           distributor = uiState.first().distributor,
                                           jenis_pengajuan = uiState.first().jenis_pengajuan,
                                           alamat = uiState.first().alamat
                                       ))
                                    }
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "CONTOH")
                    Spacer(modifier = Modifier.height(12.dp))
                   if (joinState.isNotEmpty()) {
                       joinState.first().list.forEach {
                           Text(text = "Nama: "+it.namaObat,
                               color = Color.Black)
                           Text(text = "Jumlah: "+it.jumlahObat,
                               color = Color.Black)
                           Text(text = "Distributor: "+joinState.first().distributor.distributor,
                               color = Color.Black)
                       }
                   }
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
        }
    }
}
