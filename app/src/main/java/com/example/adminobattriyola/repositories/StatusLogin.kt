package com.example.adminobattriyola.repositories

import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.models.UserLogin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StatusLogin @Inject constructor(private val dao:Dao) {
    fun getStatus():Flow<List<UserLogin>> = dao.getStatusLogin()
    suspend fun insertStatuss(status:UserLogin) = dao.insertStatus(status = status)
    suspend fun logoutAccount() = dao.logoutAccount()
}