package com.example.adminobattriyola.widgets.riwayatpengajuan

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.view.navigation.AppRoute

@Composable
fun ListItemRiwayat(
    titleDate:String,
    boolean:Boolean,
    tipePengajuan:String,
    distributor:String,
    tanggal:String,
    navController: NavController,
    openItem:() -> Unit
) {

    val animateIcon by animateIntAsState(targetValue = if (boolean) R.drawable.arrow_down else R.drawable.arrow_right)
    val listItem = remember {
        mutableStateOf(4)
    }
    val expand = remember {
        mutableStateOf(false)
    }

    Surface(
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(1.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable { openItem.invoke() }
    ) {
        Column (
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = titleDate,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onPrimary,
                    fontSize = 14.sp)
                Icon(painter = painterResource(id = animateIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp),
                    tint = MaterialTheme.colors.onPrimary)
            }
            AnimatedVisibility(visible = boolean) {
                Column {
                    Spacer(modifier = Modifier.height(14.dp))
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.2F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    for (i in 0..if (expand.value) listItem.value else 2) {
                        RiwayatChildItem(tipePengajuan, distributor, tanggal) {
                            navController.navigate(AppRoute.DetailPengajuan.route)
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                    }
                    if (listItem.value > 2) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                        ) {
                            Text(
                                text = if (expand.value) "Secukupnya" else "Selengkapnya",
                                style = MaterialTheme.typography.caption,
                                fontSize = 10.sp,
                                color = MaterialTheme.colors.onPrimary.copy(0.7F),
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable {
                                    expand.value = !expand.value
                                    Log.d("EXPAND VALUE: ",expand.value.toString())
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }

        }
    }

}