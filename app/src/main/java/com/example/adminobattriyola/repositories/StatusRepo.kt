package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.models.status.StatusResponse
import com.example.adminobattriyola.netowrk.ObatApi
import javax.inject.Inject

class StatusRepo @Inject constructor(private val api:ObatApi) {
    suspend fun getStatusPengajuan(proses:String): StatusResponse = api.getStatusPengajuan(proses)
}