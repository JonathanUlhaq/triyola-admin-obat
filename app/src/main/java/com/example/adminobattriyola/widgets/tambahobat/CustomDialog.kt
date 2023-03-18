package com.example.adminobattriyola.widgets.tambahobat

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonDropDown
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.DistributorViewModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel

@Composable
fun CustomDialog(
    boolean: MutableState<Boolean>,
    cancel: () -> Unit,
    dismiss: () -> Unit,
    confirm: () -> Unit
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
            boolean.value = false
            dismiss.invoke()
        }) {
            DialogContent(cancel = {
                cancel.invoke()
            }) {
                confirm.invoke()
            }
        }
    }
}

@Composable
fun DialogContent(
    cancel: () -> Unit,
    confirm: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.surface,

        ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 24.dp, start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.trash_confirm_icon),
                    contentDescription = null,
                    tint = MaterialTheme.colors.surface.copy(0.4F),
                    modifier = Modifier
                        .size(32.dp)
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(R.string.confirm_delete),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.error.copy(0.2F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { cancel.invoke() }) {
                    Text(
                        text = stringResource(R.string.tidak),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { confirm.invoke() }) {
                    Text(
                        text = stringResource(R.string.iya),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.error
                    )
                }
            }
        }
    }
}

@Composable
fun UpdateDialog(
    model: TambahObatViewModel,
    id: Int,
    type: String,
    name: String,
    quantity: String,
    boolean: MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateDialogUI(model = model, type = type, name = name, quantity = quantity, batal = {
                boolean.value = false
                model.obatCurrentName.value = ""
                model.obatCurrentQuantity.value = ""
                model.obatCurrentType.value = ""
            }) {
                if (name.isNotEmpty() && quantity.isNotEmpty() && type.isNotEmpty()) {
                    model.insertData(
                        TambahObatModel(
                            id = id,
                            namaObat = model.obatCurrentName.value,
                            jenisObat = model.obatCurrentType.value,
                            jumlahObat = model.obatCurrentQuantity.value,
                            satuanObat = model.unitCurrentType.value
                        )
                    )
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
                }
            }
        }
    }
}

@Composable
fun UpdateDialogUI(
    model: TambahObatViewModel,
    type: String,
    name: String,
    quantity: String,
    batal: () -> Unit,
    ubah: () -> Unit
) {
    val scroll = rememberScrollState()
    model.obatCurrentName.value = name
    model.obatCurrentQuantity.value = quantity
    model.obatCurrentType.value = type
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

    val jumlahObat = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.verticalScroll(scroll)

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
                    modifier = Modifier
                        .focusRequester(namaObat),
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
                    modifier = Modifier
                        .focusRequester(jumlahObat),
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

            Spacer(modifier = Modifier.height(32.dp))
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

@Composable
fun SaveConfirmDialog(
    boolean: MutableState<Boolean>,
    confirm: () -> Unit
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
            boolean.value = false
        }) {
            SaveConfirmDialogUI(cancel = { boolean.value = false }) {
                confirm.invoke()
                boolean.value = false
            }
        }
    }
}

@Composable
fun SaveConfirmDialogUI(
    cancel: () -> Unit,
    confirm: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.surface,

        ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 24.dp, start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.confirm_add_obat),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(R.string.confirm_add_obat_question),
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(28.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onPrimary.copy(0.2F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { cancel.invoke() }) {
                    Text(
                        text = stringResource(R.string.tidak),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { confirm.invoke() }) {
                    Text(
                        text = stringResource(R.string.iya),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun AdviceDialog(
    boolean: MutableState<Boolean>
) {
    if (boolean.value)
        Dialog(onDismissRequest = { boolean.value = false }) {
            AdviceDialogUI {
                boolean.value = false
            }
        }
}

@Composable
fun AdviceDialogUI(
    close: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.surface,

        ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 24.dp, start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.panduan),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.question_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary.copy(0.6F),
                        modifier = Modifier
                            .size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.guide_1))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_1_desc))
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.guide_2))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_2_desc))
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.guide_3_a))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_3_b))
                            }
                            append(stringResource(R.string.guide_3_c))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_3_d))
                            }
                        })
                }

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onPrimary.copy(0.2F)),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { close.invoke() }) {
                    Text(
                        text = stringResource(R.string.tutup),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun AdviceDialogTambahObat(
    boolean: MutableState<Boolean>
) {
    if (boolean.value)
        Dialog(onDismissRequest = { boolean.value = false }) {
            AdviceDialogUITambahObat {
                boolean.value = false
            }
        }
}

@Composable
fun AdviceDialogUITambahObat(
    close: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        backgroundColor = MaterialTheme.colors.onSurface,
        contentColor = MaterialTheme.colors.surface,

        ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, end = 24.dp, start = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.panduan),
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.width(14.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.question_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary.copy(0.6F),
                        modifier = Modifier
                            .size(14.dp)
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.tambah_obat_guide_a_a))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_a_b))
                            }
                            append(stringResource(R.string.tambah_obat_guide_a_c))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_a_d))
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.tambah_obat_guide_b_a))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_b_b))
                            }
                            append(stringResource(R.string.tambah_obat_guide_b_c))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_b_d))
                            }
                        })
                }

                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.tambah_obat_guide_c_a))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_c_b))
                            }
                            append(stringResource(R.string.tambah_obat_guide_c_c))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.tambah_obat_guide_c_d))
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.guide_1))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_1_desc))
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                            .size(12.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.guide_2))
                            withStyle(
                                style = SpanStyle(
                                    color = MaterialTheme.colors.onPrimary,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(stringResource(R.string.guide_2_desc))
                            }
                        })
                }

            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onPrimary.copy(0.2F)),
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = { close.invoke() }) {
                    Text(
                        text = stringResource(R.string.tutup),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun UpdateDialogDistributor(
    model: DistributorViewModel,
    id: Int,
    type: String,
    name: String,
    address: String,
    boolean: MutableState<Boolean>,
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateDialogDistributorUI(
                model = model,
                type = type,
                name = name,
                address = address,
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
    batal: () -> Unit,
    ubah: () -> Unit
) {
    model.distributorCurrentName.value = name
    model.distributorCurrentAddress.value = address
    model.currentPengajuanType.value = type
    val listPengajuan = listOf(
        "Psikotropika",
        "Reguler"
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
                    text = "Ubah Informasi Distributor",
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


@Composable
fun UpdateDialogPengajuan(
    model: PengajuanObatViewModel,
    id: Int,
    distributor_id: Int,
    type: String,
    name: String,
    quantity: String,
    boolean: MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateDialogPengajuanUI(
                model = model,
                type = type,
                name = name,
                quantity = quantity,
                batal = {
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
                }) {
                if (name.isNotEmpty() && quantity.isNotEmpty() && type.isNotEmpty()) {
                    model.insertData(
                        PengajuanObat(
                            id = id,
                            namaObat = model.obatCurrentName.value,
                            jenisObat = model.obatCurrentType.value,
                            jumlahObat = model.obatCurrentQuantity.value,
                            satuanObat = model.unitCurrentType.value,
                            distributorId = distributor_id
                        )
                    )
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
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
    batal: () -> Unit,
    ubah: () -> Unit
) {
    val scroll = rememberScrollState()
    model.obatCurrentName.value = name
    model.obatCurrentQuantity.value = quantity
    model.obatCurrentType.value = type
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
