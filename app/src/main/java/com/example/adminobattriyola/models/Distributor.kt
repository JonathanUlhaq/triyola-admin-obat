package com.example.adminobattriyola.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Distributor(
    @PrimaryKey()
    val distributor:String = "",
    val jenis_pengajuan:String ="",
    val alamat:String=""
)
