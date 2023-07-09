package com.example.adminobattriyola.widgets.pengajuan

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonDropDown
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel

@Composable
fun UpdateDialogPengajuan(
    model: PengajuanObatViewModel,
    id: Int,
    distributor_id: Int,
    type: String,
    name: String,
    quantity: String,
    dosis:String,
    satuan:String,
    boolean: MutableState<Boolean>,
    isError:MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateDialogPengajuanUI(
                model = model,
                type = type,
                name = name,
                dosis = dosis,
                satuan = satuan,
                quantity = quantity,
                error = isError.value,
                batal = {
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
                    model.currentDosis.value = ""
                }) {
                if (model.obatCurrentName.value.isNotEmpty() &&  model.obatCurrentQuantity.value.isNotEmpty() &&  model.obatCurrentType.value.isNotEmpty() && model.currentDosis.value.isNotEmpty() && model.unitCurrentType.value.isNotEmpty()) {
                    model.insertData(
                        PengajuanObat(
                            id = id,
                            namaObat = model.obatCurrentName.value,
                            jenisObat = model.obatCurrentType.value,
                            jumlahObat = model.obatCurrentQuantity.value,
                            satuanObat = model.unitCurrentType.value,
                            dosis = model.currentDosis.value,
                            distributorId = distributor_id
                        )
                    )
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
                    model.currentDosis.value = ""
                } else {
                    isError.value = true
                }
            }
        }
    }
}

@Composable
fun UpdateDialogPengajuanUI(
    model: PengajuanObatViewModel,
    type: String,
    name: String,
    quantity: String,
    dosis:String,
    error:Boolean,
    satuan: String,
    batal: () -> Unit,
    ubah: () -> Unit
) {
    val scroll = rememberScrollState()
    model.obatCurrentName.value = name
    model.obatCurrentQuantity.value = quantity
    model.obatCurrentType.value = type
    model.currentDosis.value = dosis
    model.unitCurrentType.value = satuan

    val listObat = listOf(
        stringResource(R.string.sirup),
        stringResource(R.string.tablet),
        stringResource(R.string.injeksi)
    )
    val listUnit = listOf(
        stringResource(R.string.box),
        stringResource(R.string.flash),
        stringResource(R.string.ampul),
        stringResource(R.string.pcs)
    )
    val namaObat = remember {
        FocusRequester()
    }
    val dosis = remember {
        FocusRequester()
    }
    val jumlahObat = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.ubah_obat),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(24.dp))
                ButtonDropDown(dropDown = model.booleanUpdate,
                    poli = model.obatCurrentType,
                    listObat = listObat,
                    icon = R.drawable.obat_type,
                    focusNext = {
                        namaObat.requestFocus()
                    }) {
                    model.booleanUpdate.value = !model.booleanUpdate.value
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = model.obatCurrentName,
                    label = stringResource(R.string.nama_obat),
                    icon = R.drawable.obat,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Text,
                    isError = false,
                    modifier = Modifier.focusRequester(namaObat),
                    onDone = {
                        jumlahObat.requestFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = model.currentDosis,
                    label = stringResource(R.string.dosis_obat),
                    icon = R.drawable.dosis_obat_icon,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Number,
                    isError = false,
                    modifier = Modifier.focusRequester(dosis),
                    onDone = {
                        jumlahObat.requestFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = model.obatCurrentQuantity,
                    label = stringResource(R.string.jumlah_obat),
                    icon = R.drawable.obat_quantity,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Number,
                    isError = false,
                    modifier = Modifier.focusRequester(jumlahObat),
                    onDone = {
                        focusManager.clearFocus()
                        model.booleanUpdateUnit.value = true
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ButtonDropDown(
                    dropDown = model.booleanUpdateUnit,
                    poli = model.unitCurrentType,
                    listObat = listUnit,
                    icon = R.drawable.unit_icon
                ) {
                    model.booleanUpdateUnit.value = !model.booleanUpdateUnit.value
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            AnimatedVisibility(visible = error,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)) {
                Text(text = "* Mohon form dilengkapi",
                    style = MaterialTheme.typography.caption,
                    color = Color.Black.copy(0.8f))
            }
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onPrimary.copy(0.05F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { batal.invoke() }) {
                    Text(
                        text = stringResource(R.string.tidak),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { ubah.invoke() }) {
                    Text(
                        text = "Ubah",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}