package com.example.adminobattriyola.widgets.tambahobat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.components.ButtonDropDown
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.DistributorViewModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel

@Composable
fun FormAddObat(
    model: TambahObatViewModel,
    add: () -> Unit,
    boolean: Boolean,
    remove: () -> Unit
) {
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
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            ButtonDropDown(
                dropDown = model.booleanAddForm,
                poli = model.obatType,
                listObat = listObat,
                icon = R.drawable.obat_type
            ) {
                model.booleanAddForm.value = !model.booleanAddForm.value
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.obatName,
                label = stringResource(R.string.nama_obat),
                icon = R.drawable.obat,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Text,
                isError = boolean
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.obatQuantity,
                label = stringResource(R.string.jumlah_obat),
                icon = R.drawable.obat_quantity,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Number,
                isError = boolean
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonDropDown(
                dropDown = model.booleanUpdateUnit,
                poli = model.unitType,
                listObat = listUnit,
                icon = R.drawable.unit_icon
            ) {
                model.booleanUpdateUnit.value = !model.booleanUpdateUnit.value
            }
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
                        text = stringResource(R.string.urungkan)
                    ) {
                        remove.invoke()
                    }
                    Spacer(modifier = Modifier.width(12.dp))

                    ButtonClickSecond(
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface,
                        text = stringResource(R.string.tambah)
                    ) {
                        add.invoke()
                    }
                }
            }
        }
    }
}

@Composable
fun FormPengajuanDistributor(
    model: DistributorViewModel,
    add: () -> Unit
) {
    val listPengajuan = listOf(
        "Psikotropika",
        "Reguler"
    )
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(14.dp)
        ) {
            ButtonDropDown(
                dropDown = model.booleanAddForm,
                poli = model.pengajuanType,
                listObat = listPengajuan,
                icon = R.drawable.obat_type,
                emptyText = "Jenis Pengajuan"
            ) {
                model.booleanAddForm.value = !model.booleanAddForm.value
            }
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.distributorName,
                label = "Distributor",
                icon = R.drawable.distributor_icon,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Text,
                isError = false
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextFields(
                value = model.distributorAddress,
                label = "Alamat",
                icon = R.drawable.address_icon,
                color = MaterialTheme.colors.onPrimary,
                keyboardType = KeyboardType.Text,
                isError = false
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
                        backgroundColor = MaterialTheme.colors.primary,
                        contentColor = MaterialTheme.colors.onSurface,
                        text = stringResource(R.string.tambah)
                    ) {
                        add.invoke()
                    }
                }
            }
        }
    }
}

