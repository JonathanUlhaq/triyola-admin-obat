package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.models.PengajuanObat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PengajuanObatRepo @Inject constructor(private val dao: Dao) {
    fun getAllData(distributor:String): Flow<List<PengajuanObat>> = dao.getPengajuanObat(distributor)
    suspend fun deleteAllData() = dao.deleteAllData()
    suspend fun deleteObatbyId(obat:PengajuanObat) = dao.deletePengajuanObatById(obat)
    suspend fun insertData(obat: PengajuanObat) = dao.insertPengajuanObat(obat)
    suspend fun updateData(obat: PengajuanObat) = dao.updatePengajuanObat(obat)
}