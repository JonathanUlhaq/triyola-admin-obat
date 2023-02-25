package com.example.adminobattriyola.view.daftarobat

import androidx.compose.animation.*
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.SearchField

@Suppress("FrequentlyChangedStateReadInComposition")
@Composable
fun DaftarObatScreen(
    viewModel: DaftarObatViewModel
) {

    val state = rememberLazyListState()

    val percent = viewModel.getPercentList(
        state.firstVisibleItemIndex,
        viewModel.obatType.size,
        state.layoutInfo.visibleItemsInfo.size
    )


    val buttonText by animateIntAsState(targetValue = if (viewModel.enable.value) R.string.simpan else R.string.ubah)
    val buttonIcon by animateIntAsState(targetValue = if (viewModel.enable.value) R.drawable.download_icon else R.drawable.edit_icon)

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
                            .offset(y = 20.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally)
                    ) {
                        Text(
                            text = stringResource(R.string.title_daftar_obat),
                            style = MaterialTheme.typography.h1,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Surface(
                            color = MaterialTheme.colors.onPrimary,
                            border = BorderStroke(7.dp, MaterialTheme.colors.background),
                            shape = CircleShape,
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
                                ObatCategory(category = item,
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
                                SmallButton(
                                    boolean = viewModel.enable.value,
                                    text = stringResource(buttonText),
                                    icon = buttonIcon
                                ) {
                                    viewModel.enable.value = !viewModel.enable.value
                                }
                                Spacer(modifier = Modifier.height(14.dp))
                                LazyColumn(
                                    state = state,
                                    content = {
                                        itemsIndexed(viewModel.obatName) { index, item ->
                                            ListObat(
                                                name = item,
                                                type = viewModel.obatType[index],
                                                amount = viewModel.currentAmount[index],
                                                boolean = viewModel.enable.value,
                                                addClick = {
                                                    viewModel.currentAmount[index] =
                                                        viewModel.currentAmount[index] + 1

                                                },
                                                removeClick = {
                                                    viewModel.currentAmount[index] =
                                                        viewModel.currentAmount[index] - 1
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ListObat(
    name: String,
    type: String,
    amount: Int,
    boolean: Boolean,
    addClick: () -> Unit,
    removeClick: () -> Unit
) {

    Surface(
        color = Color.Transparent,
        border = BorderStroke(2.dp, MaterialTheme.colors.onPrimary),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp, bottom = 14.dp, start = 18.dp, end = 18.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = type,
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.onPrimary
                )
            }

            AnimatedContent(targetState = boolean,
                transitionSpec = {
                    fadeIn(tween(700)) with
                            fadeOut(tween(700))
                }) { target ->
                if (target) {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Row(
                            verticalAlignment = CenterVertically
                        ) {
                            Text(
                                text = stringResource(R.string.jumlah_text),
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onPrimary,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colors.onPrimary,
                                contentColor = MaterialTheme.colors.onSurface
                            ) {
                                Text(
                                    text = amount.toString(),
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(
                                            start = 14.dp,
                                            end = 14.dp,
                                            top = 4.dp,
                                            bottom = 4.dp
                                        )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row(
                            verticalAlignment = CenterVertically,
                        ) {
                            OptionButton(
                                removeClick,
                                R.drawable.icon_minus,
                                MaterialTheme.colors.error
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            OptionButton(
                                addClick,
                                R.drawable.icon_add,
                                MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                } else {
                    Column(
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.jumlah_text),
                            style = MaterialTheme.typography.body2,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
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
                                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OptionButton(
    onClick: () -> Unit,
    icon: Int,
    color: Color
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = MaterialTheme.colors.onSurface
        )
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(8.dp)
        )
    }
}

@Composable
fun SmallButton(
    boolean: Boolean,
    text: String,
    icon: Int,
    click: () -> Unit
) {

    val backgroundColor by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.secondary else MaterialTheme.colors.primary)

    Button(
        onClick = { click.invoke() },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults
            .buttonColors(
                backgroundColor = backgroundColor,
                disabledBackgroundColor = MaterialTheme.colors.primary.copy(0.5F),
                contentColor = MaterialTheme.colors.onSurface,
                disabledContentColor = MaterialTheme.colors.onSurface
            ),
        enabled = true,
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(14.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.body2
        )

    }
}

@Composable
fun ObatCategory(
    category: String,
    boolean: Boolean,
    currentIndex: Int,
    index: (Int) -> Unit
) {

    val backgroundColor by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onBackground)
    val fontColor by animateColorAsState(
        targetValue = if (boolean) MaterialTheme.colors.onSurface else MaterialTheme.colors.surface.copy(
            0.8F
        )
    )

    IconButton(onClick = { index.invoke(currentIndex) }) {
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = backgroundColor,
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = category,
                modifier = Modifier
                    .padding(8.dp),
                style = MaterialTheme.typography.body2,
                color = fontColor
            )
        }
    }

}

