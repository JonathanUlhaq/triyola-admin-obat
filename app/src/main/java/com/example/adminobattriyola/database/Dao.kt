package com.example.adminobattriyola.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.relations.DistributorWithObat
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Query("SELECT * FROM tb_tambahobat")
    fun getAllData(): Flow<List<TambahObatModel>>


    @Query("SELECT * FROM tb_tambahobat WHERE id = :id")
    fun getDataById(id: Int): Flow<List<TambahObatModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(model: TambahObatModel)

    @Delete
    suspend fun deleteData(model: TambahObatModel)

    @Query("DELETE FROM tb_tambahobat")
    suspend fun deleteAllData()

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateData(model: TambahObatModel)

    //    Distributor
    @Query("SELECT * FROM Distributor")
    fun getDistributor():Flow<List<Distributor>>

    @Delete
    suspend fun deleteDistributor(distributor: Distributor)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistributor(distributor: Distributor)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateDistributor(distributor: Distributor)

//    Pengajuan Obat
    @Transaction
    @Query("SELECT * FROM Distributor")
    fun getPengajuanObat():Flow<List<DistributorWithObat>>
    @Query("DELETE FROM PengajuanObat")
    suspend fun deletePengajuanObat()
    @Delete
    suspend fun deletePengajuanObatById(obat:PengajuanObat)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPengajuanObat(obat: PengajuanObat)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePengajuanObat(obat: PengajuanObat)
}