package com.example.adminobattriyola.view.tambahobat

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.*
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.util.Vibrate
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.widgets.tambahobat.CustomDialog
import com.example.adminobattriyola.widgets.tambahobat.SaveConfirmDialog
import com.example.adminobattriyola.widgets.tambahobat.UpdateDialog
import kotlinx.coroutines.launch

@Composable
fun TambahObatScreen(
    navController: NavController,
    model: TambahObatViewModel,
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

    SaveConfirmDialog(boolean = saveDialogShow ) {
        navController.navigate(AppRoute.DaftarObat.route) {
            popUpTo(0)
        }
        model.deleteAllData()
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        bottomBar = {

                    ButtonClickSecond(backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface ,
                        text = "Simpan",
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 20.dp)
                            .fillMaxWidth()) {
                            saveDialogShow.value =true
                    }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tambah_obat),
                        style = MaterialTheme.typography.h1,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onSurface
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = null,
                            tint = MaterialTheme.colors.onSurface,
                            modifier = Modifier
                                .size(12.dp)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.onPrimary,
                elevation = 0.dp
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                        .background(MaterialTheme.colors.onPrimary)
                        .height(30.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Column(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    ListObat(uiState, model, addForm, isError) {
                        model.deleteDatabyId(it)
                    }

                }

            }
        }
    }
}

@Composable
fun ListObat(
    uiState: List<TambahObatModel>,
    model: TambahObatViewModel,
    addForm: MutableState<Boolean>,
    isError: MutableState<Boolean>,
    onDelete:(TambahObatModel) -> Unit
) {

    LazyColumn(content = {
        itemsIndexed(uiState) { index, item ->
            ItemObat(
                value = item,
                model
            ) {
                onDelete.invoke(item)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            AnimatedVisibility(visible = addForm.value) {
                FormAddObat(
                    model, boolean = isError.value,
                    add = {
                        if (model.obatType.value.isNotEmpty() && model.obatName.value.isNotEmpty() && model.obatQuantity.value.isNotEmpty()) {
                            model.insertData(
                                TambahObatModel(
                                    jenisObat = model.obatType.value,
                                    namaObat = model.obatName.value,
                                    jumlahObat = model.obatQuantity.value
                                )
                            )
                            addForm.value = false
                            model.obatType.value = ""
                            model.obatName.value = ""
                            model.obatQuantity.value = ""
                            isError.value = false
                        } else {
                            isError.value = true
                        }
                    }) {
                    addForm.value = false
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(visible = !addForm.value) {
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
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
private fun ItemObat(
    value: TambahObatModel,
    model: TambahObatViewModel,
    delete:() -> Unit
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

    UpdateDialog(model = model, id = value.id, type = value.jenisObat , name = value.namaObat , quantity = value.jumlahObat , boolean = showUpdateDialog )


    if (dismissState.isDismissed(DismissDirection.EndToStart)){
        Vibrate(context = context)
        showDialog.value =true
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

    SwipeToDismiss(state = dismissState,
        directions = setOf(DismissDirection.EndToStart),
        dismissThresholds = {
           direction ->
           FractionalThreshold(if (direction == DismissDirection.EndToStart) 0.4F else 0.05F)
        },
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.Transparent
                    else -> MaterialTheme.colors.error
                }
            )
            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.25f else 1f
            )
            Box(
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(color)
                    .padding(horizontal = Dp(20f)),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    contentDescription = "Delete Icon",
                    modifier = Modifier
                        .size(24.dp)
                        .scale(scale),
                    tint = MaterialTheme.colors.onSurface
                )
            }
        }) {
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
                        Log.d("Pencet aku, ", "y dipencet")
                    }
                )
        ) {

            Column(
                modifier = Modifier
                    .padding(14.dp)
            ) {

                PreviewForm(icon = R.drawable.obat,
                    label = value.jenisObat )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(icon = R.drawable.obat,
                    label = value.namaObat )
                Spacer(modifier = Modifier.height(16.dp))
                PreviewForm(icon = R.drawable.obat_quantity,
                    label = value.jumlahObat )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }


}

@Composable
fun PreviewForm(
    icon:Int,
    label:String
) {
    Surface(
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(24.dp)
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun FormAddObat(
    model: TambahObatViewModel,
    add: () -> Unit,
    boolean: Boolean,
    remove: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            ButtonDropDown(dropDown = model.booleanAddForm, poli = model.obatType) {
                model.booleanAddForm.value = !model.booleanAddForm.value
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.obatName,
                label = "Nama Obat",
                icon = R.drawable.obat,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Text,
                isError = boolean
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.obatQuantity,
                label = "Jumlah Obat",
                icon = R.drawable.obat_quantity,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Number,
                isError = boolean
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    ButtonClickSecond(
                        backgroundColor = MaterialTheme.colors.error,
                        contentColor = MaterialTheme.colors.onSurface,
                        text = "Urungkan"
                    ) {
                        remove.invoke()
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    ButtonClickSecond(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface,
                        text = "Tambah"
                    ) {
                        add.invoke()
                    }
                }
            }
        }
    }
}