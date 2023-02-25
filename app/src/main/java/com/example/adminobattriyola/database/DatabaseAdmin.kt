package com.example.adminobattriyola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.adminobattriyola.models.TambahObatModel

@Database(entities = [TambahObatModel::class], version = 1)
abstract class DatabaseAdmin:RoomDatabase() {
    abstract fun getDAO():Dao
}