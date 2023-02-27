package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.models.TambahObatModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TambahObatRepo @Inject constructor(private val dao: Dao) {
    fun getAllData():Flow<List<TambahObatModel>> = dao.getAllData().flowOn(Dispatchers.IO).conflate()
    fun getDataById(id:Int):Flow<List<TambahObatModel>> = dao.getDataById(id).flowOn(Dispatchers.IO).conflate()
    suspend fun insertData(model: TambahObatModel) = dao.insertData(model)
    suspend fun deleteData(model: TambahObatModel) = dao.deleteData(model)
    suspend fun updateData(model: TambahObatModel) = dao.updateData(model)
    suspend fun deleteAllData() = dao.deleteAllData()
}