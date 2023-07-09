package com.example.adminobattriyola.view.pengajuan.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.DistributorViewModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanScreen
import com.example.adminobattriyola.view.pengajuan.riwayat.RiwayatPengajuanScreen
import com.example.adminobattriyola.view.pengajuan.statuspengajuan.StatusPengajuan
import com.example.adminobattriyola.view.pengajuan.statuspengajuan.StatusPengajuanViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PengajuanMainScreen(
    distributorViewModel: DistributorViewModel,
    obat: PengajuanObatViewModel,
    statusViewModel:StatusPengajuanViewModel,
    navController:NavController
) {
    val currentIndex = remember {
        mutableStateOf(0)
    }
    val coroutine = rememberCoroutineScope()
   Scaffold(
       backgroundColor = MaterialTheme.colors.background
   ) {
       val pagerState = rememberPagerState()
       val listPengajuan = listOf(
           "Pengajuan",
           "Status",
           "Riwayat"
       )
       val coroutine = rememberCoroutineScope()
       Surface(modifier = Modifier
           .padding(it),
            color = Color.Transparent) {
           Column {
               TabRow(selectedTabIndex = pagerState.currentPage,
                   indicator = {
                           tabPositions ->
                       TabRowDefaults.Indicator(
                           Modifier.pagerTabIndicatorOffset(pagerState,tabPositions),
                           color = MaterialTheme.colors.onBackground
                       )
                   },
                   backgroundColor = MaterialTheme.colors.onPrimary,
                   contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp))) {

                   listPengajuan.forEachIndexed { index, item ->
                       Tab(selected = pagerState.currentPage == index,
                           onClick = {
                               coroutine.launch {
                                   pagerState.animateScrollToPage(index)
                               }
                           },
                           text = {
                               Text(text = item,
                                   style = MaterialTheme.typography.body1)
                           },
                           modifier = Modifier
                               .padding(8.dp))
                   }
               }
               HorizontalPager(count = listPengajuan.size,
                                state = pagerState) { index ->
                   currentIndex.value = index
                   when(index) {
                       0 -> {
                           PengajuanScreen(distributorViewModel,obat) {
                               coroutine.launch {
                                   pagerState.scrollToPage(1)
                               }
                           }
                       }
                       1 -> {
                           StatusPengajuan(navController,statusViewModel)
                       }
                       2 -> {
                           RiwayatPengajuanScreen(navController = navController)
                       }
                   }

               }
           }
       }
   }
}