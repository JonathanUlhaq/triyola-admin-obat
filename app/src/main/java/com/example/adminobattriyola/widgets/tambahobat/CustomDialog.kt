package com.example.adminobattriyola.widgets.tambahobat

import android.icu.text.ListFormatter.Width
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClickSecond
import com.example.adminobattriyola.components.ButtonDropDown
import com.example.adminobattriyola.components.OutlinedTextFields
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.view.tambahobat.DetailTambahObatViewModel
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
                    text = "Apakah anda yakin ingin menghapus data ini ?",
                    style = MaterialTheme.typography.body1,
                    textAlign = TextAlign.Center
                )

            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onPrimary.copy(0.2F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { cancel.invoke() }) {
                    Text(
                        text = "Tidak",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { confirm.invoke() }) {
                    Text(
                        text = "Iya",
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
fun UpdateDialog(
    model: TambahObatViewModel,
    id:Int,
    type: String,
    name:String,
    quantity:String,
    boolean: MutableState<Boolean>
) {
    if (boolean.value) {
        Dialog(onDismissRequest = {
        }
      ) {
           UpdateDialogUI(model = model , type = type , name = name , quantity = quantity, batal = {
               boolean.value = false
               model.obatCurrentName.value = ""
               model.obatCurrentQuantity.value = ""
               model.obatCurrentType.value = ""
           } ) {
              if (name.isNotEmpty() && quantity.isNotEmpty() && type.isNotEmpty()) {
                  model.insertData(TambahObatModel(
                      id = id,
                      namaObat = model.obatCurrentName.value,
                      jenisObat =  model.obatCurrentType.value,
                      jumlahObat =  model.obatCurrentQuantity.value
                  ))
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
    name:String,
    quantity:String,
    batal:() -> Unit,
    ubah: () -> Unit
) {
    model.obatCurrentName.value = name
    model.obatCurrentQuantity.value = quantity
    model.obatCurrentType.value = type
    Surface(
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(20.dp)
    ) {
       Column {
           Column(
               modifier = Modifier
                   .padding(start = 16.dp, end = 16.dp, top = 16.dp),
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Text(text = "Ubah Obat",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary)
               Spacer(modifier = Modifier.height(24.dp))
               ButtonDropDown(dropDown = model.booleanUpdate, poli = model.obatCurrentType) {
                   model.booleanUpdate.value = !model.booleanUpdate.value
               }
               Spacer(modifier = Modifier.height(16.dp))
               OutlinedTextFields(
                   value = model.obatCurrentName,
                   label = "Nama Obat",
                   icon = R.drawable.obat,
                   color = MaterialTheme.colors.onPrimary,
                   keyboardType = KeyboardType.Text,
                   isError = false
               )
               Spacer(modifier = Modifier.height(16.dp))
               OutlinedTextFields(
                   value = model.obatCurrentQuantity,
                   label = "Jumlah Obat",
                   icon = R.drawable.obat_quantity,
                   color = MaterialTheme.colors.onPrimary,
                   keyboardType = KeyboardType.Number,
                   isError = false
               )
           }

            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface.copy(0.05F)),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                TextButton(onClick = { batal.invoke()  }) {
                    Text(
                        text = "Tidak",
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
                Text(text = "Konfirmasi Penambahan Data Obat",
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Apakah anda yakin ingin menambahkan data - data tersebut ?",
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
                        text = "Tidak",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color =  MaterialTheme.colors.surface.copy(0.4F)
                    )
                }
                TextButton(onClick = { confirm.invoke() }) {
                    Text(
                        text = "Iya",
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
    close:() -> Unit
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
                    Text(text = "Panduan",
                        style = MaterialTheme.typography.h2,
                        color = MaterialTheme.colors.onPrimary)
                    Spacer(modifier = Modifier.width(14.dp))
                    Icon(painter = painterResource(id = R.drawable.question_icon),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onPrimary.copy(0.6F),
                        modifier = Modifier
                            .size(14.dp))
                }

                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                        .size(12.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1 ,
                        text = buildAnnotatedString {
                        append("Tap tahan pada form obat yang telah terisi untuk melakukan ")
                        withStyle(style = SpanStyle(
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = FontWeight.Bold)) {
                            append("Update Data")
                        }
                    })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                        .size(12.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1 ,
                        text = buildAnnotatedString {
                            append("Swipe kiri pada form obat yang telah terisi untuk melakukan ")
                            withStyle(style = SpanStyle(
                                color = MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold)) {
                                append("Penghapusan Data")
                            }
                        })
                }
                Spacer(modifier = Modifier.height(28.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colors.onPrimary.copy(0.8F))
                        .size(12.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        style = MaterialTheme.typography.body1 ,
                        text = buildAnnotatedString {
                            append("Tap ")
                            withStyle(style = SpanStyle(
                                color = MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold)) {
                                append("tambah form obat + ")
                            }
                            append("untuk ")
                            withStyle(style = SpanStyle(
                                color = MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold)) {
                                append("Menambahkan form obat")
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
                        text = "Tutup",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}
