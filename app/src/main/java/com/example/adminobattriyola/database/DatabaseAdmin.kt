package com.example.adminobattriyola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.models.UserLogin

@Database(entities = [TambahObatModel::class,PengajuanObat::class,Distributor::class,UserLogin::class], version = 30)
abstract class DatabaseAdmin:RoomDatabase() {
    abstract fun getDAO():Dao
}