package com.example.adminobattriyola.models.status

data class Pengajuan(
    val jenis:String? = null,
    val tanggal:String? = null,
    val distributor:String? = null,
    val status:String? = null,
    val alamat:String? = null,
    val obat_ajuan:List<ObatAjuan>? = null
)
