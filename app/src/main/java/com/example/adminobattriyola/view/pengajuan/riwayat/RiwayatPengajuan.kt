package com.example.adminobattriyola.view.pengajuan.riwayat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.ButtonClick
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.widgets.daftarobat.Category

@Composable
fun RiwayatPengajuanScreen(
    navController: NavController
) {

    val tahun = listOf(
        2023,
        2022,
        2021,
        2020,
        2019,
        2018
    )
    val listItem = listOf(
        "Januari 2023",
        "Februari 2023",
        "April 2023",
        "Maret 2023"
    )
    val selected = remember {
        mutableStateOf(false)
    }

    val currentIndex = remember {
        mutableStateOf(0)
    }

    val selectedItem = remember {
        mutableStateOf(false)
    }

    val currentItem = remember {
        mutableStateOf<Int?>(null)
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .padding(top = 14.dp),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 14.dp)
            ) {
                LazyRow(content = {
                    itemsIndexed(tahun) { index, item ->
                        selected.value = index == currentIndex.value
                        Category(
                            category = item.toString(),
                            boolean = selected.value,
                            currentIndex = index,
                            index = { currentIndex.value = it })
                    }
                })

                Spacer(modifier = Modifier.height(18.dp))
                Column(
                    modifier = Modifier
                        .padding(end = 14.dp)
                ) {
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.2F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    LazyColumn(content = {
                        itemsIndexed(listItem) { index,item ->
                            selectedItem.value = index == currentItem.value
                            ListItemRiwayat(titleDate = item,selectedItem.value,"Reguler","Wayne Enterprise","03/03/2023",navController) {
                                if (currentItem.value == index) {
                                    currentItem.value = null
                                } else {
                                    currentItem.value = index
                                }
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    })
                }
            }
        }
    }
}

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

    Surface(
        color = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary,
        border = BorderStroke(1.dp,MaterialTheme.colors.onPrimary),
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
                   for (i in 0..2) {
                       RiwayatChildItem(tipePengajuan, distributor, tanggal) {
                        navController.navigate(AppRoute.DetailPengajuan.route)
                       }
                       Spacer(modifier = Modifier.height(14.dp))
                   }
               }
           }

        }
    }

}

@Composable
fun RiwayatChildItem(
    tipePengajuan: String,
    distributor: String,
    tanggal: String,
    onClick:() -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.surface.copy(0.2F))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = tipePengajuan,
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.body2
            )
        }
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = distributor,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.body2
        )
        Divider(
            color = MaterialTheme.colors.surface.copy(0.2F),
            modifier = Modifier
                .width(1.dp)
                .height(14.dp)
                .clip(RoundedCornerShape(50))
        )
        Text(
            text = tanggal,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.body2
        )
    }
    Spacer(modifier = Modifier.height(18.dp))
    ButtonClick(
        backgroundColor = MaterialTheme.colors.secondary.copy(0.6F),
        contentColor = MaterialTheme.colors.onSurface,
        text = "Detail",
        modifier = Modifier.fillMaxWidth(),
        roundedeCorners = 6,
        fontSizes = 14
    ) {
        onClick.invoke()
    }
}
