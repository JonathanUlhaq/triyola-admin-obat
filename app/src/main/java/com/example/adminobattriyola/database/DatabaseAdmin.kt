package com.example.adminobattriyola.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.adminobattriyola.models.TambahObatModel
import com.example.adminobattriyola.util.UUIDConverter

@Database(entities = [TambahObatModel::class], version = 9)
abstract class DatabaseAdmin:RoomDatabase() {
    abstract fun getDAO():Dao
}