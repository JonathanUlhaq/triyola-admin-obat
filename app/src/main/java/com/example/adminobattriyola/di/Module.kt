package com.example.adminobattriyola.di

import android.content.Context
import androidx.room.Room
import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.database.DatabaseAdmin
import com.example.adminobattriyola.netowrk.AuthInterceptor
import com.example.adminobattriyola.netowrk.ObatApi
import com.example.adminobattriyola.util.ConstUrl
import com.example.adminobattriyola.util.SharePref
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

    @Singleton
    @Provides
    fun okhttpProvide(auth:AuthInterceptor,pref:SharePref): OkHttpClient {
        val token = pref.getToken()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(auth)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun retrofitProvider(client: OkHttpClient): ObatApi =
        Retrofit.Builder()
            .baseUrl(ConstUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ObatApi::class.java)

    @Singleton
    @Provides
    fun databaseProvide(@ApplicationContext context: Context): DatabaseAdmin {
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
    fun daoProvide(database: DatabaseAdmin): Dao {
        return database.getDAO()
    }


    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context):Context =
        context

}