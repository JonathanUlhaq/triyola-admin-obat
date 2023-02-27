package com.example.adminobattriyola.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.adminobattriyola.models.TambahObatModel
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM tb_tambahobat")
    fun getAllData():Flow<List<TambahObatModel>>

    @Query("SELECT * FROM tb_tambahobat WHERE id = :id")
    fun getDataById(id:Int):Flow<List<TambahObatModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(model: TambahObatModel)

    @Delete
    suspend fun deleteData(model: TambahObatModel)

    @Query("DELETE FROM tb_tambahobat")
    suspend fun deleteAllData()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(model: TambahObatModel)
}