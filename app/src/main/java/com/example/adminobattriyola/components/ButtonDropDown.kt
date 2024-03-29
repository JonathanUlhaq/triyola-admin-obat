package com.example.adminobattriyola.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R

@Composable
fun ButtonDropDown(
    dropDown: MutableState<Boolean>,
    poli: MutableState<String>,
    listObat:List<String>,
    icon:Int,
    emptyText:String = "Jenis Obat",
    focusNext:() -> Unit = {},
    onCLick:() -> Unit ={}

) {



    val icons by animateIntAsState(targetValue = if (dropDown.value) R.drawable.arrow_down else R.drawable.arrow_right)


    Surface(
        elevation = 0.dp,
        shape = RoundedCornerShape(15.dp),
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.primaryVariant,
        border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .clickable {
                onCLick.invoke() }
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = poli.value.ifEmpty { emptyText },
                        style = MaterialTheme.typography.h1,
                        fontSize = 12.sp,
                    )
                }

                Icon(
                    painter = painterResource(id = icons),
                    contentDescription = null,
                    modifier = Modifier
                        .size(10.dp)
                )
            }


            AnimatedVisibility(visible = dropDown.value) {
                Column {
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.18F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                    )
                    listObat.forEachIndexed { index, item->
                        Spacer(modifier = Modifier.height(14.dp))
                        Surface(
                            color = Color.Transparent,
                            contentColor = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    poli.value = item
                                    dropDown.value = false
                                    focusNext.invoke()
                                }
                        ) {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.h1,
                                fontSize = 12.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        if (index < listObat.size - 1) {
                            Divider(
                                color = MaterialTheme.colors.surface.copy(0.18F),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(100))
                            )
                        }
                    }
                }
            }
        }
    }
}