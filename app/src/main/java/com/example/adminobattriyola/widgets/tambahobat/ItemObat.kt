package com.example.adminobattriyola.widgets.tambahobat

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.components.SwipeDismissEffect
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.util.Vibrate
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel
import com.example.adminobattriyola.widgets.pengajuan.UpdateDialogPengajuan
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun ItemObat(
    value: TambahObatModel,
    model: TambahObatViewModel,
    delete: () -> Unit
) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    val showUpdateDialog = remember {
        mutableStateOf(false)
    }
    val coroutine = rememberCoroutineScope()
    val dismissState = rememberDismissState()
    val context = LocalContext.current

    UpdateDialog(
        model = model,
        id = value.id,
        type = value.jenisObat,
        name = value.namaObat,
        quantity = value.jumlahObat,
        boolean = showUpdateDialog
    )


    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        Vibrate(context = context)
        showDialog.value = true
        CustomDialog(boolean = showDialog, cancel = {
            showDialog.value = false
            coroutine.launch {
                dismissState.reset()
            }
        },
            dismiss = {
                coroutine.launch {
                    dismissState.reset()
                }
            }) {
            delete.invoke()
            showDialog.value = false
            coroutine.launch {
                dismissState.reset()
            }

        }
    }
    SwipeDismissEffect(dismissState) {
        Surface(
            color = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.onPrimary,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .combinedClickable(
                    onLongClick = {
                        showUpdateDialog.value = true
                    },
                    onClick = {
                        Log.d("TAP, ", "Tap")
                    }
                )
        ) {

            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {

                PreviewForm(
                    icon = R.drawable.obat,
                    label = value.jenisObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.obat,
                    label = value.namaObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.obat_quantity,
                    label = value.jumlahObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.unit_icon,
                    label = value.satuanObat
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun ItemObatPengajuan(
    value: PengajuanObat,
    model: PengajuanObatViewModel,
    delete: () -> Unit
) {

    val showUpdateDialog = remember {
        mutableStateOf(false)
    }

    val distributorId = model.uiState.collectAsState().value.first().distributor.id

    UpdateDialogPengajuan(
        model = model,
        id = value.id,
        distributor_id = distributorId ,
        type = value.jenisObat,
        name = value.namaObat,
        quantity = value.jumlahObat,
        boolean = showUpdateDialog
    )

        Surface(
            color = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.onPrimary,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.clickable { showUpdateDialog.value = true }
        ) {

            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {

                PreviewForm(
                    icon = R.drawable.obat,
                    label = value.jenisObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.obat,
                    label = value.namaObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.obat_quantity,
                    label = value.jumlahObat
                )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(
                    icon = R.drawable.unit_icon,
                    label = value.satuanObat
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
                            text = stringResource(id = R.string.hapus)
                        ) {
                            delete.invoke()
                        }

                }
            }
        }

}