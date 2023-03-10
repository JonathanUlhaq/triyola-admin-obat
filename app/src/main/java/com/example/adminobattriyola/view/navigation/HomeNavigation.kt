package com.example.adminobattriyola.view.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.adminobattriyola.view.daftarobat.DaftarObatScreen
import com.example.adminobattriyola.view.daftarobat.DaftarObatViewModel
import com.example.adminobattriyola.view.pengajuan.main.PengajuanMainScreen
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.DistributorViewModel
import com.example.adminobattriyola.view.pengajuan.pengajuanscreen.PengajuanObatViewModel
import com.example.adminobattriyola.view.riwayatobat.RiwayatObatScreen
import com.example.adminobattriyola.view.riwayatobat.RiwayatObatViewModel
import com.example.adminobattriyola.view.tambahobat.DetailTambahObatViewModel
import com.example.adminobattriyola.view.tambahobat.TambahObatScreen
import com.example.adminobattriyola.view.tambahobat.TambahObatViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigation(
    navController: NavHostController,
    hideBotNavBar:MutableState<Boolean>,
    hideFAB:MutableState<Boolean>,
    mainObat:MutableState<Boolean>
) {
    val systemUiController = rememberSystemUiController()
    val daftarObat = hiltViewModel<DaftarObatViewModel>()
    val tambahObat = hiltViewModel<TambahObatViewModel>()
    val riwayatObat = hiltViewModel<RiwayatObatViewModel>()
    val distributor = hiltViewModel<DistributorViewModel>()
    val pengajuanObat = hiltViewModel< PengajuanObatViewModel>()
    AnimatedNavHost(navController = navController, startDestination = AppRoute.DaftarObat.route) {
        composable(AppRoute.DaftarObat.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            mainObat.value = true
            hideFAB.value = false
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = false
            DaftarObatScreen(daftarObat)
        }

        composable(AppRoute.TambahObat.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = true
            TambahObatScreen(navController,tambahObat)
        }
        composable(AppRoute.History.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            mainObat.value = false
            hideFAB.value = true
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = false
            RiwayatObatScreen(riwayatObat)
        }
        composable(AppRoute.Pengajuan.route,
            enterTransition = {fadeIn(tween(700))}
        ) {
            mainObat.value = false
            hideFAB.value = true
            systemUiController.setStatusBarColor(
                color = MaterialTheme.colors.primaryVariant
            )

            systemUiController.setNavigationBarColor(
                color = MaterialTheme.colors.background
            )
            hideBotNavBar.value = false
            PengajuanMainScreen(distributor,pengajuanObat)
        }


    }
}