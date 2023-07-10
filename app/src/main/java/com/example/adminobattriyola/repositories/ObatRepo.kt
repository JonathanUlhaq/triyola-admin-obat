package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.models.obat.ObatResponse
import com.example.adminobattriyola.netowrk.ObatApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObatRepo @Inject constructor(private val api:ObatApi) {
    suspend fun getObat():ObatResponse = api.getObat()
    suspend fun cariObat(search:String):ObatResponse = api.cariObat(search)
    suspend fun getObatDetail(type:String) = api.getDetailObat(type)
}