package com.example.adminobattriyola.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_login")
data class UserLogin(
    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id:Int,
    @ColumnInfo(name = "status_login")
    var status_login:Boolean

)
