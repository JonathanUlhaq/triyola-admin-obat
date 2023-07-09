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
import androidx.compose.runtime.mutableStateOf
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
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.DistributorViewModel

@Composable
fun UpdateDialogDistributor(
    model: DistributorViewModel,
    type: String,
    name: String,
    address: String,
    boolean: MutableState<Boolean>,
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            val isError = remember {
                mutableStateOf(false)
            }
            UpdateDialogDistributorUI(
                model = model,
                type = type,
                name = name,
                address = address,
                error = isError.value,
                batal = {
                    boolean.value = false
                    model.distributorCurrentName.value = ""
                    model.distributorCurrentAddress.value = ""
                    model.currentPengajuanType.value = ""
                }) {
                if (model.distributorCurrentName.value.isNotEmpty() && model.distributorCurrentAddress.value.isNotEmpty() && model.currentPengajuanType.value.isNotEmpty()) {
                    model.updateData(
                        Distributor(
                            id = model.uiState.value.first().id,
                            distributor = model.distributorCurrentName.value,
                            alamat = model.distributorCurrentAddress.value,
                            jenis_pengajuan = model.currentPengajuanType.value
                        )
                    )
                    boolean.value = false
                    model.distributorCurrentName.value = ""
                    model.distributorCurrentAddress.value = ""
                    model.currentPengajuanType.value = ""
                } else {
                    isError.value = true
                }
            }
        }
    }
}

@Composable
fun UpdateDialogDistributorUI(
    model: DistributorViewModel,
    type: String,
    name: String,
    address: String,
    error:Boolean,
    batal: () -> Unit,
    ubah: () -> Unit
) {
    model.distributorCurrentName.value = name
    model.distributorCurrentAddress.value = address
    model.currentPengajuanType.value = type
    val listPengajuan = listOf(
        stringResource(R.string.psikotropika),
        stringResource(R.string.reguler)
    )
    val distributor = remember {
        FocusRequester()
    }

    val alamat = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val scroll = rememberScrollState()
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
                    text = stringResource(R.string.distributor_update_desc),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(24.dp))
                ButtonDropDown(
                    dropDown = model.booleanUpdate,
                    poli = model.currentPengajuanType,
                    listObat = listPengajuan,
                    icon = R.drawable.obat_type,
                    focusNext = {
                        distributor.requestFocus()
                    }
                ) {
                    model.booleanUpdate.value = !model.booleanUpdate.value
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = model.distributorCurrentName,
                    label = "Distributor",
                    icon = R.drawable.distributor_icon,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Text,
                    isError = false,
                    modifier = Modifier
                        .focusRequester(distributor),
                    onDone = {
                        alamat.requestFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = model.distributorCurrentAddress,
                    label = "Alamat",
                    icon = R.drawable.address_icon,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Text,
                    isError = false,
                    modifier = Modifier
                        .focusRequester(alamat),
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
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