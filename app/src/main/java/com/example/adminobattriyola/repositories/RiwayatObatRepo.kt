package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.models.riwayat.RiwayatResponse
import com.example.adminobattriyola.netowrk.ObatApi
import javax.inject.Inject

class RiwayatObatRepo @Inject constructor(private val api:ObatApi) {
    suspend fun getRiwayatTahun(tahun:String):RiwayatResponse = api.lihatTransaksiObat(tahun)
}