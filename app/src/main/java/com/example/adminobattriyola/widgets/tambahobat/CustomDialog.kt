package com.example.adminobattriyola.widgets.tambahobat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.models.obat.Obat
import com.example.adminobattriyola.view.daftarobat.DaftarObatViewModel
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
    dosis: String,
    unit:String,
    boolean: MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateDialogUI(unit = unit,model = model, type = type, name = name, quantity = quantity, dosis = dosis , batal = {
                boolean.value = false
                model.obatCurrentName.value = ""
                model.obatCurrentQuantity.value = ""
                model.obatCurrentType.value = ""
                model.dosisObatCurrent.value = ""
            }) {
                if (name.isNotEmpty() && quantity.isNotEmpty() && type.isNotEmpty()) {
                    model.insertData(
                        TambahObatModel(
                            id = id,
                            namaObat = model.obatCurrentName.value,
                            jenisObat = model.obatCurrentType.value,
                            jumlahObat = model.obatCurrentQuantity.value,
                            satuanObat = model.unitCurrentType.value,
                            dosisObat = model.dosisObatCurrent.value
                        )
                    )
                    boolean.value = false
                    model.obatCurrentName.value = ""
                    model.obatCurrentQuantity.value = ""
                    model.obatCurrentType.value = ""
                    model.dosisObatCurrent.value = ""
                }
            }
        }
    }
}

@Composable
fun UpdateTransaksi(
    model: DaftarObatViewModel,
    obat:MutableState<String>,
    jenis:MutableState<String>,
    dosis: MutableState<String>,
    unit:MutableState<String>,
    jumlah:MutableState<String>,
    boolean: MutableState<Boolean>
) {



    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
        ) {
            UpdateTransaksiUI(
                obat = obat,
                dosis = dosis,
                jenisObat = jenis,
                satuan = unit,
                jumlah = jumlah, batal = {
//                    obat.value = ""
//                    jenisObat.value = ""
//                    satuan.value = ""
//                    dosie.value = ""
//                    jumlah.value = ""
                boolean.value = false
            }) {
//                    model.insertData(
//                        TambahObatModel(
//                            id = id,
//                            namaObat = model.obatCurrentName.value,
//                            jenisObat = model.obatCurrentType.value,
//                            jumlahObat = model.obatCurrentQuantity.value,
//                            satuanObat = model.unitCurrentType.value,
//                            dosisObat = model.dosisObatCurrent.value
//                        )
//                    )
                    boolean.value = false


            }
        }
    }
}

@Composable
fun UpdateTransaksiUI(
    obat:MutableState<String>,
    jenisObat:MutableState<String>,
    dosis:MutableState<String>,
    satuan:MutableState<String>,
    jumlah:MutableState<String>,
    batal: () -> Unit,
    ubah: () -> Unit
) {
    val update = remember {
        mutableStateOf(false)
    }
    val scroll = rememberScrollState()
    val showError = remember {
        mutableStateOf(false)
    }



//    model.obatCurrentName.value = name
//    model.obatCurrentQuantity.value = quantity
//    model.obatCurrentType.value = type
//    model.dosisObatCurrent.value = dosis
//    model.unitCurrentType.value = unit

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
    val dosisObat = remember {
        FocusRequester()
    }
    val jumlahObat = remember {
        FocusRequester()
    }
    val updateUnit = remember {
        mutableStateOf(false)
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
                ButtonDropDown(dropDown = update,
                    poli = jenisObat,
                    listObat = listObat,
                    icon = R.drawable.obat_type,
                    focusNext = {
                        namaObat.requestFocus()
                    }) {
                    update.value = !update.value
                }
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = obat,
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
                    value = dosis,
                    label = stringResource(R.string.dosis_obat),
                    icon = R.drawable.dosis_obat_icon,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Number,
                    isError = false,
                    modifier = Modifier.focusRequester(dosisObat),
                    onDone = {
                        jumlahObat.requestFocus()
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextFields(
                    value = jumlah,
                    label = stringResource(R.string.jumlah_obat),
                    icon = R.drawable.obat_quantity,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Number,
                    isError = false,
                    modifier = Modifier
                        .focusRequester(jumlahObat),
                    onDone = {
                        focusManager.clearFocus()
                        updateUnit.value = true
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                ButtonDropDown(
                    dropDown = updateUnit,
                    poli =satuan,
                    listObat = listUnit,
                    icon = R.drawable.unit_icon
                ) {
                    updateUnit.value = !updateUnit.value
                }
                Spacer(modifier = Modifier.height(2.dp))
                AnimatedVisibility(visible = showError.value) {
                    Text(text = "* Mohon form dilengkapi",
                        style = MaterialTheme.typography.caption,
                        color = Color.Black.copy(0.8f))
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
                TextButton(onClick = {
                    if (jenisObat.value.isNotEmpty() && jumlah.value.isNotEmpty() && obat.value.isNotEmpty() && dosis.value.isNotEmpty() && satuan.value.isNotEmpty()) {
                        ubah.invoke()
                    } else {
                        showError.value = true
                    }

                }) {
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
fun UpdateDialogUI(
    unit:String,
    model: TambahObatViewModel,
    type: String,
    name: String,
    quantity: String,
    dosis:String,
    batal: () -> Unit,
    ubah: () -> Unit
) {
    val scroll = rememberScrollState()
    val showError = remember {
        mutableStateOf(false)
    }

    model.obatCurrentName.value = name
    model.obatCurrentQuantity.value = quantity
    model.obatCurrentType.value = type
    model.dosisObatCurrent.value = dosis
    model.unitCurrentType.value = unit

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
    val dosisObat = remember {
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
                    value = model.dosisObatCurrent,
                    label = stringResource(R.string.dosis_obat),
                    icon = R.drawable.dosis_obat_icon,
                    color = MaterialTheme.colors.onPrimary,
                    keyboardType = KeyboardType.Number,
                    isError = false,
                    modifier = Modifier.focusRequester(dosisObat),
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
                Spacer(modifier = Modifier.height(2.dp))
                AnimatedVisibility(visible = showError.value) {
                    Text(text = "* Mohon form dilengkapi",
                        style = MaterialTheme.typography.caption,
                        color = Color.Black.copy(0.8f))
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
                TextButton(onClick = {
                    if (model.obatCurrentType.value.isNotEmpty() && model.obatCurrentQuantity.value.isNotEmpty() && model.obatCurrentName.value.isNotEmpty() && model.dosisObatCurrent.value.isNotEmpty() && model.unitCurrentType.value.isNotEmpty()) {
                        ubah.invoke()
                    } else {
                        showError.value = true
                    }

                }) {
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
fun LoadingDialog(
    boolean: MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
            boolean.value = true
        }) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
fun SaveConfirmDialog(
    title: String = "Konfirmasi Penambahan Data Obat",
    desc: String = "Apakah anda yakin ingin menambahkan data - data tersebut ?",
    boolean: MutableState<Boolean>,
    confirm: () -> Unit
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
            boolean.value = false
        }) {
            SaveConfirmDialogUI(
                title = title,
                desc = desc,
                cancel = { boolean.value = false }) {
                confirm.invoke()
                boolean.value = false
            }
        }
    }
}

@Composable
fun SaveConfirmDialogUI(
    title:String,
    desc:String,
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
                    text = title,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = desc,
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







