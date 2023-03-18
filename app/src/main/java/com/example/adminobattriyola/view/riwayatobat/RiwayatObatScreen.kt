package com.example.adminobattriyola.view.riwayatobat

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.widgets.daftarobat.Category

@Composable
fun RiwayatObatScreen(
    viewModel: RiwayatObatViewModel
) {
    val showDetail = remember {
        mutableStateOf(false)
    }
    val currentIndex = remember {
        mutableStateOf<Int?>(null)
    }
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,

        ) { paddingValue ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier
                .padding(paddingValue)
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .height(120.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                            .background(MaterialTheme.colors.onPrimary)
                            .height(80.dp)
                    )

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(y = 20.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.title_riwayat_obat),
                            style = MaterialTheme.typography.h1,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            color = MaterialTheme.colors.onPrimary,
                            border = BorderStroke(3.dp, MaterialTheme.colors.background),
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(8.dp),
                            elevation = 0.dp
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(MaterialTheme.colors.onPrimary)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp)
                ) {
                    Column {
                        LazyRow(content = {
                            itemsIndexed(viewModel.riwayatCategory) { index, item ->
                                viewModel.selectedCategory.value =
                                    index == viewModel.currentIndex.value
                                Category(
                                    category = item,
                                    boolean = viewModel.selectedCategory.value,
                                    currentIndex = index,
                                    index = {
                                        viewModel.currentIndex.value = it
                                    })
                            }
                        })
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .padding(end = 14.dp)
                        ) {
                            Divider(
                                color = MaterialTheme.colors.surface.copy(0.2F),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(50))
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            LazyColumn(content = {
                                items(10) {
                                    showDetail.value = it == currentIndex.value
                                    RiwayatObatContent(showDetail.value, contentDetailTransaksi = {
                                        for (i in 0..2) {
                                            ContentDetail(status = "Keluar")
                                            Spacer(modifier = Modifier.height(8.dp))
                                            ContentDetail(status = "Masuk")
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                    }) {
                                        if (currentIndex.value == it) {
                                            currentIndex.value = null
                                        } else {
                                            currentIndex.value = it
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
    }
}

@Composable
fun RiwayatObatContent(boolean: Boolean,contentDetailTransaksi:@Composable () -> Unit, onClick:() -> Unit) {

    val animateIcon by animateIntAsState(targetValue = if (boolean) R.drawable.arrow_down else R.drawable.arrow_right)

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .clickable { 
                onClick.invoke()
            }
    ) {
        Column (
            modifier = Modifier
                .padding(22.dp)
                ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Januari 2023",
                    style = MaterialTheme.typography.h1,
                    fontSize = 14.sp ,
                    color = MaterialTheme.colors.onPrimary
                )

                Icon(painter = painterResource(id = animateIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(12.dp),
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
                    Spacer(modifier = Modifier.height(18.dp))
                    contentDetailTransaksi.invoke()
                }
            }
        }
    }
}

@Composable
fun ContentDetail(status:String) {
    val animateColor by animateColorAsState(targetValue = if (status == "Keluar") MaterialTheme.colors.error else MaterialTheme.colors.onPrimary)
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = animateColor,
        elevation = 0.dp
    ) {
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "Hufagrip",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Hufagrip",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface)
            }
            Column(
                horizontalAlignment = Alignment.Start,
            ) {
                Text(text = "$status: 4",
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 14.sp)
                Spacer(modifier = Modifier.height(14.dp))
                Text(text = "Box",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface)
            }
            Text(text = "03/03/2023",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp)
        }
    }
}