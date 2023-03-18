package com.example.adminobattriyola.view.daftarobat

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.SearchField
import com.example.adminobattriyola.util.Vibrate
import com.example.adminobattriyola.widgets.daftarobat.ListObat
import com.example.adminobattriyola.widgets.daftarobat.Category
import com.example.adminobattriyola.widgets.tambahobat.TambahObatIconFAQ

@Suppress("FrequentlyChangedStateReadInComposition")
@Composable
fun DaftarObatScreen(
    viewModel: DaftarObatViewModel
) {

    val state = rememberLazyListState()
    val listObat = viewModel.selectedObatList.value
    val percent = viewModel.getPercentList(
        state.firstVisibleItemIndex,
        viewModel.obatType.size,
        state.layoutInfo.visibleItemsInfo.size
    )
    val context = LocalContext.current
    val vibrateState = remember {
        mutableStateOf(false)
    }

    val multipleSelect = remember {
        mutableStateOf(false)
    }

    val showAdviceDialog = remember {
        mutableStateOf(false)
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
                        horizontalAlignment = CenterHorizontally,
                        modifier = Modifier
                            .offset(y = 16.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.title_daftar_obat),
                            style = MaterialTheme.typography.h1,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            color = MaterialTheme.colors.onPrimary,
                            border = BorderStroke(7.dp, MaterialTheme.colors.background),
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(8.dp),
                            elevation = 0.dp
                        ) {
                            TambahObatIconFAQ(showAdviceDialog)
                        }
                    }
                }

                Surface(
                    color = Color.Transparent,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 14.dp)
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    Column {
                        AnimatedVisibility(visible = (percent < 10.0)) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 14.dp)
                                ) {
                                    SearchField(
                                        value = viewModel.searchValue,
                                        icon = R.drawable.icon_search,
                                        label = "Cari obat"
                                    )
                                }

                                Spacer(modifier = Modifier.height(12.dp))
                            }
                        }
                        LazyRow(content = {
                            itemsIndexed(viewModel.categoryList) { index, item ->
                                viewModel.selected.value = index == viewModel.currentIndex.value
                                Category(category = item,
                                    boolean = viewModel.selected.value,
                                    currentIndex = index,
                                    index = {
                                        viewModel.currentIndex.value = index
                                    })
                            }
                        })
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 12.dp)
                        ) {
                            Column {
                                Divider(
                                    color = MaterialTheme.colors.surface.copy(0.2F),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(50))
                                )
                                Spacer(modifier = Modifier.height(14.dp))

                                AnimatedVisibility(visible = (multipleSelect.value)) {
                                    Column {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = CenterVertically
                                        ) {
                                            Text(text = stringResource(R.string.batalkan),
                                                color = MaterialTheme.colors.primary,
                                                style = MaterialTheme.typography.body1,
                                                modifier = Modifier
                                                    .clickable {
                                                        viewModel.selectedObatList.value =
                                                            listObat.map {
                                                                it.copy(isSelected = false)
                                                            }
                                                        multipleSelect.value = false
                                                    })

                                            Text(text = stringResource(R.string.bersihkan),
                                                color = MaterialTheme.colors.primary,
                                                style = MaterialTheme.typography.body1,
                                                modifier = Modifier
                                                    .clickable {
                                                        viewModel.selectedObatList.value =
                                                            listObat.map {
                                                                it.copy(isSelected = false)
                                                            }
                                                    })
                                        }
                                        Spacer(modifier = Modifier.height(14.dp))
                                    }
                                }
                                LazyColumn(
                                    state = state,
                                    content = {
                                        items(viewModel.obatName.size) { index ->
                                            if (vibrateState.value) {
                                                Vibrate(context = context)
                                                vibrateState.value = false
                                            }
                                            ListObat(
                                                name = viewModel.obatName[index],
                                                type = viewModel.obatType[index],
                                                amount = viewModel.currentAmount[index],
                                                boolean = listObat[index].isSelected,
                                                multipleSelect = multipleSelect.value,
                                                onClick = {
                                                    if (multipleSelect.value) {
                                                        viewModel.selectedObatList.value =
                                                            listObat.mapIndexed { itemIndex, item ->
                                                                if (index == itemIndex) {
                                                                    item.copy(isSelected = !item.isSelected)
                                                                } else item
                                                            }
                                                    }

                                                },
                                                onLongClick = {
                                                    vibrateState.value = true
                                                    multipleSelect.value = true
                                                }
                                            )
                                            Spacer(modifier = Modifier.height(14.dp))
                                        }
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}





