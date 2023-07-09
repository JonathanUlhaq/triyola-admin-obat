package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.models.addobat.AddObatResponse
import com.example.adminobattriyola.netowrk.ObatApi
import javax.inject.Inject

class AddObatRepo @Inject constructor(private val api: ObatApi) {
    suspend fun addObat(
        namaObat: String,
        jenisObat: String,
        satuan: String,
        dosis: String
    ): AddObatResponse = api.addObat(
        nama_obat = namaObat,
        jenis_obat = jenisObat,
        satuan_obat = satuan,
        dosis_obat = dosis
    )

    suspend fun addTransaksiObat(
        obat_id:Int,
        satuan: String,
        jumlah:Int,
        jenis_transaksi:String
    ) = api.addTransaksiObat(
        obat_id = obat_id,
        satuan = satuan,
        jumlah = jumlah,
        jenis_transaksi = jenis_transaksi
    )
}