package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.models.Distributor
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DistributorRepo @Inject constructor(private val dao: Dao) {
    fun getAllData():Flow<List<Distributor>> = dao.getDistributor()
    suspend fun deleteAllData(distributor: Distributor) = dao.deleteDistributor(distributor)
    suspend fun insertData(distributor: Distributor) = dao.insertDistributor(distributor)
    suspend fun updateData(distributor: Distributor) = dao.updateDistributor(distributor)
}