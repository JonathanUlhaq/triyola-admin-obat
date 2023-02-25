package com.example.adminobattriyola.database

import androidx.room.Query
import com.example.adminobattriyola.models.TambahObatModel
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM tb_tambahobat")
    fun getAllData():Flow<List<TambahObatModel>>
}