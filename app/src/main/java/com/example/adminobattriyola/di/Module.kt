package com.example.adminobattriyola.di

import android.content.Context
import androidx.room.Room
import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.database.DatabaseAdmin
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

    @Singleton
    @Provides
    fun databaseProvide(@ApplicationContext context:Context):DatabaseAdmin {
        return Room.databaseBuilder(
            context,
            DatabaseAdmin::class.java,
            "database_local_admin"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun daoProvide(database:DatabaseAdmin):Dao {
        return database.getDAO()
    }

}