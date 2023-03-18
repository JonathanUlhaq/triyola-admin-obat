package com.example.adminobattriyola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat
import com.example.adminobattriyola.models.TambahObatModel

@Database(entities = [TambahObatModel::class,PengajuanObat::class,Distributor::class], version = 25)
abstract class DatabaseAdmin:RoomDatabase() {
    abstract fun getDAO():Dao
}