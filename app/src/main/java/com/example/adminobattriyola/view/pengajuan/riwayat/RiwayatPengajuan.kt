package com.example.adminobattriyola.view.pengajuan.riwayat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.widgets.daftarobat.Category
import com.example.adminobattriyola.widgets.riwayatpengajuan.ListItemRiwayat

@Composable
fun RiwayatPengajuanScreen(
    navController: NavController
) {

    val year = listOf(
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
                    itemsIndexed(year) { index, item ->
                        selected.value = index == currentIndex.value
                        Category(
                            category = item.toString(),
                            boolean = selected.value,
                            currentIndex = index,
                            index = {indexs ->
                                currentIndex.value = indexs })
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




