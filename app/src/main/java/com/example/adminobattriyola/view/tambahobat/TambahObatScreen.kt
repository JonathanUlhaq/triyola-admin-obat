package com.example.adminobattriyola.view.tambahobat

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.BackIcon
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.components.TextTitle
import com.example.adminobattriyola.view.daftarobat.DaftarObatViewModel
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.widgets.tambahobat.IconFAQ
import com.example.adminobattriyola.widgets.tambahobat.LoadingDialog
import com.example.adminobattriyola.widgets.tambahobat.SaveConfirmDialog
import com.example.adminobattriyola.widgets.tambahobat.TambahObatMain
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@Composable
fun TambahObatScreen(
    navController: NavController,
    model: TambahObatViewModel,
    daftarObat:DaftarObatViewModel
) {
    model.getAllData()
    val uiState = model.uiState.collectAsState().value
    val addForm = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val saveDialogShow = remember {
        mutableStateOf(false)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    SaveConfirmDialog(boolean = saveDialogShow) {
        runBlocking {
            isLoading.value = true
            uiState.forEach {
                    item ->
                model.insertObatSever(
                    jenisObat = item.jenisObat,
                    namaObat = item.namaObat,
                    satuan = item.satuanObat,
                    dosis = item.dosisObat,
                    isError = isError,
                ) {
                    it.data?.let {
                        model.insertTransaksiObat(
                            obat_id = it.id!!,
                            satuan = item.satuanObat,
                            jumlah = item.jumlahObat.toInt(),
                            jenis_transaksi = "Masuk"
                        ) {
                            isLoading.value = false
                            if (!isError.value) {
                                Toast.makeText(context,"Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                                navController.navigate(AppRoute.DaftarObat.route) {
                                    popUpTo(0)
                                }
//                                daftarObat.getObat()
                                model.deleteAllData()
                            } else {
                                Toast.makeText(context,"Kesalahn Server, data gagal ditambahkan, coba lagi",
                                    Toast.LENGTH_LONG).show()
                            }

                        }
                    }
                }
                delay(1000)
            }.let {
                isLoading.value = false


            }
        }

    }

    LoadingDialog(boolean = isLoading)

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(stringResource(R.string.tambah_obat))
                },
                navigationIcon = {
                    BackIcon(navController)
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp,
                actions = {
                    val showAdviceDialog = remember {
                        mutableStateOf(false)
                    }
                    IconFAQ(showAdviceDialog)
                }
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            TambahObatMain(uiState, model, addForm, saveDialogShow ,isError)
        }
    }
}

















