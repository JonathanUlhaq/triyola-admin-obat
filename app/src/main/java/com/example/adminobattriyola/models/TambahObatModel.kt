package com.example.adminobattriyola.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_tambahobat")
data class TambahObatModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int = 0,
    @ColumnInfo(name = "jenis_obat")
    val jenisObat:String,
    @ColumnInfo(name = "nama_obat")
    val namaObat:String,
    @ColumnInfo(name = "jumlah_obat")
    val jumlahObat:Int
)