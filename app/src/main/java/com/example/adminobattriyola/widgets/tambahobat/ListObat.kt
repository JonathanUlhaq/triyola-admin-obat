package com.example.adminobattriyola.widgets.tambahobat

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel

@Composable
fun ListObat(
    uiState: List<TambahObatModel>,
    model: TambahObatViewModel,
    addForm: MutableState<Boolean>,
    isError: MutableState<Boolean>,
    saveDialog:MutableState<Boolean>,
    onDelete: (TambahObatModel) -> Unit
) {

    val context = LocalContext.current

    LazyColumn(content = {
        items(uiState) { item ->
            ItemObat(
                value = item,
                model
            ) {
                onDelete.invoke(item)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            AnimatedVisibility(visible = addForm.value,
                enter = expandVertically(tween(700), expandFrom = Alignment.Top),
                exit = shrinkVertically(tween(700), shrinkTowards = Alignment.Top)
                ) {
                FormAddObat(
                    model, boolean = isError.value,
                    add = {
                        if (model.obatType.value.isNotEmpty() && model.obatName.value.isNotEmpty() && model.obatQuantity.value.isNotEmpty()&& model.dosisObat.value.isNotEmpty()&& model.unitType.value.isNotEmpty()) {
                            model.insertData(
                                TambahObatModel(
                                    jenisObat = model.obatType.value,
                                    namaObat = model.obatName.value,
                                    jumlahObat = model.obatQuantity.value,
                                    satuanObat = model.unitType.value,
                                    dosisObat = model.dosisObat.value
                                )
                            )
                            addForm.value = false
                            model.obatType.value = ""
                            model.obatName.value = ""
                            model.obatQuantity.value = ""
                            model.dosisObat.value = ""
                            model.unitType.value = ""
                            isError.value = false
                        } else {
                            isError.value = true
                        }
                    }) {
                    addForm.value = false
                    model.obatType.value = ""
                    model.obatName.value = ""
                    model.obatQuantity.value = ""
                    model.dosisObat.value = ""
                    model.unitType.value = ""
                    isError.value = false
                    addForm.value = false
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            AnimatedVisibility(visible = !addForm.value,
                enter = expandVertically(tween(700), expandFrom = Alignment.Top),
                exit = shrinkVertically(tween(700), shrinkTowards = Alignment.Top)) {
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
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = uiState.isNotEmpty()) {
                ButtonClick(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onSurface,
                    text = stringResource(id = R.string.simpan),
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp, top = 20.dp, bottom = 20.dp)
                        .fillMaxWidth()
                ) {
                    if (uiState.isNotEmpty()) {
                        saveDialog.value = true
                    } else {
                        Toast.makeText(context,"Belum ada obat yang akan ditambahkan",Toast.LENGTH_LONG).show()
                    }
                }
            }

        }
    })
}