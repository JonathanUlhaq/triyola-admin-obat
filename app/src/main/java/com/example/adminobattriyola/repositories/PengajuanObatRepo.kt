package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.pengajuanobat.DistributorResponse
import com.example.adminobattriyola.models.pengajuanobat.PengajuanObatResponse
import com.example.adminobattriyola.netowrk.ObatApi
import com.example.adminobattriyola.relations.DistributorWithObat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PengajuanObatRepo @Inject constructor(private val dao: Dao, private val api:ObatApi) {
    fun getAllData(): Flow<List<DistributorWithObat>> = dao.getPengajuanObat()
    suspend fun deleteAllData() = dao.deleteAllData()
    suspend fun deleteObatbyId(obat:PengajuanObat) = dao.deletePengajuanObatById(obat)
    suspend fun insertData(obat: PengajuanObat) = dao.insertPengajuanObat(obat)
    suspend fun updateData(obat: PengajuanObat) = dao.updatePengajuanObat(obat)

    suspend fun pengajuanDistributor(
        kategori_obat:String,
        nama_distributor:String,
        alamat:String
    ):DistributorResponse = api.pengajuanDistributor(
        kategori_obat,
        nama_distributor,
        alamat
    )

    suspend fun pengajuanObat(
        jenis_obat:String,
        nama_obat:String,
        dosis_obat:String,
        jumlah_obat:Int,
        satuan_obat:String,
        distributor_id:Int
    ):PengajuanObatResponse = api.pengajuanObat(
        jenis_obat = jenis_obat,
        nama_obat = nama_obat,
        dosis_obat = dosis_obat,
        jumlah_obat = jumlah_obat,
        satuan_obat = satuan_obat,
        distributor_id = distributor_id
    )
}