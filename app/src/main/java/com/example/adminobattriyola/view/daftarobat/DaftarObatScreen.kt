package com.example.adminobattriyola.view.daftarobat

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.adminobattriyola.R
import com.example.adminobattriyola.components.AccountDropDown
import com.example.adminobattriyola.components.LogoutConfirm
import com.example.adminobattriyola.components.SearchField
import com.example.adminobattriyola.util.Vibrate
import com.example.adminobattriyola.view.navigation.AppRoute
import com.example.adminobattriyola.view.navigation.NavigationViewModel
import com.example.adminobattriyola.widgets.daftarobat.ListObat
import com.example.adminobattriyola.widgets.daftarobat.Category
import com.example.adminobattriyola.widgets.daftarobat.ShimerLoading
import com.example.adminobattriyola.widgets.tambahobat.TambahObatIconFAQ
import com.example.adminobattriyola.widgets.tambahobat.UpdateTransaksi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun DaftarObatScreen(
    viewModel: DaftarObatViewModel,
    navVm: NavigationViewModel,
    navController: NavController
) {
    viewModel.getObat()
    val isRefresh by viewModel.isRefreshing.collectAsState()
    val searchValue = remember {
        mutableStateOf("")
    }
    val showDropDown = remember {
        mutableStateOf(false)
    }
    val showLogout = remember {
        mutableStateOf(false)
    }

    LogoutConfirm(
        boolean = showLogout,
        cancel = { showLogout.value = false },
        dismiss = { showLogout.value = false }) {
        navVm.pref.saveToken("")
        navController.navigate(AppRoute.SplashScreen.route) {
            popUpTo(0)
        }
        navVm.logoutAccount()
        showLogout.value = false
    }


    val uiState = viewModel.uiState.collectAsState().value
    val state = rememberLazyListState()
//    val listObat = viewModel.selectedObatList?.value
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
    val updateShowed = remember {
        mutableStateOf(false)
    }
    val obat = remember {
        mutableStateOf("")
    }
    val jenis = remember {
        mutableStateOf("")
    }
    val dosis = remember {
        mutableStateOf("")
    }
    val satuan = remember {
        mutableStateOf("")
    }
    val jumlah = remember {
        mutableStateOf("")
    }
    UpdateTransaksi(
        model = viewModel,
        jenis = jenis,
        obat = obat,
        dosis = dosis,
        unit = satuan,
        jumlah = jumlah,
        boolean = updateShowed
    )

    SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefresh), onRefresh = { viewModel.refresh() }) {

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
                            Box {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentWidth(CenterHorizontally)
                                ) {
                                    Text(
                                        text = stringResource(R.string.title_daftar_obat),
                                        style = MaterialTheme.typography.h1,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colors.onSurface
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .padding(end = 18.dp)
                                        .offset(y = 10.dp)
                                        .fillMaxWidth()
                                        .wrapContentWidth(End)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.person_icon),
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier
                                            .size(14.dp)
                                            .clickable {
                                                showDropDown.value = true
                                            }
                                    )
                                    AccountDropDown(boolean = showDropDown) {
                                        showLogout.value = true
                                    }
                                }
                            }

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
                                            value = searchValue,
                                            icon = R.drawable.icon_search,
                                            label = "Cari obat"
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(12.dp))
                                }
                            }
                            LazyRow(
                                content = {
                                    itemsIndexed(viewModel.categoryList) { index, item ->
                                        viewModel.selected.value = index == viewModel.currentIndex.value
                                        Category(category = item,
                                            boolean = viewModel.selected.value,
                                            currentIndex = index,
                                            searchValue = searchValue,
                                            index = {
                                                viewModel.currentIndex.value = index
                                            })
                                    }
                                },
                                modifier = Modifier.testTag("listCategory")
                            )
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
                                                            viewModel.selectedList.clear()
                                                            multipleSelect.value = false


                                                        })

                                                Text(text = stringResource(R.string.bersihkan),
                                                    color = MaterialTheme.colors.primary,
                                                    style = MaterialTheme.typography.body1,
                                                    modifier = Modifier
                                                        .clickable {
//                                                        if (listObat != null) {
//                                                            viewModel.selectedObatList.value =
//                                                                listObat.map {
//                                                                    it.copy(isSelected = false)
//                                                                }
//                                                        }

                                                            viewModel.selectedList.clear()
                                                        })
                                            }
                                            Spacer(modifier = Modifier.height(14.dp))
                                        }
                                    }
                                    LazyColumn(
//                                    state = state,
                                        content = {
                                            if (uiState.data != null) {
                                                itemsIndexed(uiState.data) { index, item ->
                                                    if (vibrateState.value) {
                                                        Vibrate(context = context)
                                                        vibrateState.value = false
                                                    }

                                                    ListObat(
                                                        vm = viewModel,
                                                        model = item,
                                                        updateShow = updateShowed,
                                                        id = item.id!!.toString()!!,
                                                        name = item.nama_obat!!,
                                                        type = item.jenis_obat!!,
                                                        amount = item.jumlah!!,
                                                        dosis = item.dosis_obat!!.toInt(),
                                                        satuan = item.satuan_obat!!,
                                                        boolean = viewModel.selectedList.contains(item.id!!),
                                                        multipleSelect = multipleSelect.value,
                                                        onClick = {
                                                            if (multipleSelect.value) {
//                                                           viewModel.selectedObatList.value =
//                                                               listObat.mapIndexed { itemIndex, items ->
//                                                                   if (index == itemIndex) {
//                                                                       items.copy(isSelected = !items.isSelected)
//                                                                   } else items
//                                                               }
//                                                           if (listObat[index].isSelected) {
//                                                               viewModel.selectedList.add(item.id!!)
//
//                                                           }
                                                                if (viewModel.selectedList.contains(item.id)) {
                                                                    viewModel.selectedList.remove(item.id)
                                                                } else {
                                                                    viewModel.selectedList.add(item.id)
                                                                }

                                                            } else {
                                                                obat.value = item.nama_obat
                                                                jenis.value = item.jenis_obat
                                                                dosis.value = item.dosis_obat
                                                                satuan.value = item.satuan_obat
                                                                jumlah.value = item.jumlah.toString()
                                                                updateShowed.value = true
                                                            }

                                                        },
                                                        onLongClick = {
                                                            vibrateState.value = true
                                                            multipleSelect.value = true
                                                        }
                                                    )
                                                    Spacer(modifier = Modifier.height(14.dp))
                                                }
                                            } else {
                                                items(15) {
                                                    ShimerLoading()
                                                    Spacer(modifier = Modifier.height(14.dp))
                                                }
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

}





