package com.example.adminobattriyola.widgets.daftarobat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.models.obat.Obat
import com.example.adminobattriyola.view.daftarobat.DaftarObatViewModel
import com.example.adminobattriyola.widgets.tambahobat.UpdateTransaksi

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListObat(
    vm:DaftarObatViewModel,
    model:Obat,
    updateShow:MutableState<Boolean>,
    id: String,
    name: String,
    type: String,
    amount: Int,
    dosis:Int,
    satuan:String,
    boolean: Boolean,
    multipleSelect:Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit
) {


    val colorSelected by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.secondary.copy(0.4F) else MaterialTheme.colors.onBackground)

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .combinedClickable(
                onClick = { onClick.invoke() },
                onLongClick = { onLongClick.invoke() }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (modifier = Modifier
                .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
               Row {
                   Text(
                       text = type,
                       style = MaterialTheme.typography.caption,
                       color = MaterialTheme.colors.onPrimary
                   )
                   Spacer(modifier = Modifier.width(2.dp))
                   Text(
                       text = dosis.toString() + "mg",
                       style = MaterialTheme.typography.caption,
                       color = MaterialTheme.colors.onPrimary
                   )
               }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colors.onPrimary,
                        contentColor = MaterialTheme.colors.onSurface
                    ) {
                        Text(
                            text = amount.toString(),
                            style = MaterialTheme.typography.h1,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = satuan,
                    style = MaterialTheme.typography.body2,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onPrimary
                )

            }

            AnimatedVisibility(visible = multipleSelect) {
                Box(modifier = Modifier
                    .size(80.dp)
                    .background(colorSelected))
            }
        }
    }
}