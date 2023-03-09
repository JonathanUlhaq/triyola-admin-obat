package com.example.adminobattriyola.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.*
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Distributor::class,
            childColumns = arrayOf("distributorId"),
            parentColumns = arrayOf("id"),
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class PengajuanObat(
    @PrimaryKey(autoGenerate = true)
    var id:Int? = 0,
    var jenisObat:String = "",
    var namaObat:String = "",
    var jumlahObat:String = "",
    var satuanObat:String = "",
    @ColumnInfo(index = true)
    var distributorId:Int = 0
)
