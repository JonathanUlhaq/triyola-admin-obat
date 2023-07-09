package com.example.adminobattriyola

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.adminobattriyola.database.Dao
import com.example.adminobattriyola.database.DatabaseAdmin
import com.example.adminobattriyola.models.TambahObatModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTesting {


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var dao:Dao
    lateinit var database:DatabaseAdmin
    val testDispatcher = TestCoroutineDispatcher()
    @OptIn(ExperimentalCoroutinesApi::class)
    val testScope = TestCoroutineScope((testDispatcher))

    @Before
    fun setup () {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            DatabaseAdmin::class.java
        ).build()
        dao = database.getDAO()
    }

    @After
    fun tearDown() {
        database.close()
    }


//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun insertData () {
//        testScope.runBlockingTest {
//            val tambahObat = TambahObatModel(id = 23,jenisObat = "asdasd", namaObat = "asdas", jumlahObat = "fasddd", satuanObat = "gfds")
//            dao.insertData(tambahObat)
//            val getAllData = dao.getAllData()
//            val contain = getAllData.distinctUntilChanged().first().contains(tambahObat)
//            if (contain) {
//                Log.d("containCreate: ","true")
//            } else {
//                Log.d("containCreate: ","false")
//            }
//        }
//    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun updateDataTest() {
//        testScope.runBlockingTest {
//            val tambahObat = TambahObatModel(id = 1, jenisObat = "asdasd", namaObat = "asdas", jumlahObat = "fasddd", satuanObat = "gfds")
//            dao.insertData(tambahObat)
//            dao.updateData(TambahObatModel(id = 1, jenisObat = "SAPUTANGAN", namaObat = "Bodrex", jumlahObat = "2", satuanObat = "cm"))
//            val getAllData = dao.getAllData()
//            val contain = getAllData.distinctUntilChanged().first().contains(TambahObatModel(id = 1, jenisObat = "SAPUTANGAN", namaObat = "Bodrex", jumlahObat = "2", satuanObat = "cm"))
//            if (contain) {
//                Log.d("containUpdate: ","true")
//            } else {
//                Log.d("containUpdate: ","false")
//            }
//        }
//    }

//    @OptIn(ExperimentalCoroutinesApi::class)
//    @Test
//    fun deleteDataTest() {
//        testScope.runBlockingTest {
//            val tambahObat = TambahObatModel(id = 1, jenisObat = "asdasd", namaObat = "asdas", jumlahObat = "fasddd", satuanObat = "gfds")
//            dao.insertData(tambahObat)
//            dao.deleteData(tambahObat)
//            val contain = dao.getAllData().distinctUntilChanged().first().contains(tambahObat)
//            if (contain) {
//                Log.d("containDelete: ","true")
//            } else {
//                Log.d("containDelete: ","false")
//            }
//        }
//    }


}