package com.example.adminobattriyola.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PengajuanObat(
    @PrimaryKey()
    var jenisObat:String,
    var namaObat:String,
    var jumlahObat:String,
    var satuanObat:String,
    var distributor:String
)
