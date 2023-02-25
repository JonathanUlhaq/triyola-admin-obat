package com.example.adminobattriyola.view.navigation

import com.example.adminobattriyola.R

sealed class AppRoute(val route:String, val icon:Int = 0) {
    object SplashScreen:AppRoute("splashscreen")
    object LoginScreen:AppRoute("loginscreen")
    object HomeNavigation:AppRoute("homenavigation")
    object DaftarObat:AppRoute("daftarobat", R.drawable.obat)
    object History:AppRoute("history", R.drawable.history)
    object Pengajuan:AppRoute("pengajuan", R.drawable.pengajuan)
    object TambahObat:AppRoute("tambahobat")
}
