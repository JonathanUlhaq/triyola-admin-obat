package com.example.adminobattriyola.netowrk

import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.NetObatModel
import com.example.adminobattriyola.models.addobat.AddObatResponse
import com.example.adminobattriyola.models.login.LoginResponse
import com.example.adminobattriyola.models.obat.ObatResponse
import com.example.adminobattriyola.models.pengajuanobat.DistributorResponse
import com.example.adminobattriyola.models.pengajuanobat.PengajuanObatResponse
import com.example.adminobattriyola.models.riwayat.RiwayatResponse
import com.example.adminobattriyola.models.status.StatusResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ObatApi {

    @FormUrlEncoded
    @POST("/api/otp")
    suspend fun loginAdmin(@Field("telepon")telepon:String)

    @FormUrlEncoded
    @POST("/api/distributor")
    suspend fun pengajuanDistributor(
        @Field("kategori_obat") kategori_obat:String,
        @Field("nama_distributor")nama_distributor:String,
        @Field("alamat")alamat:String
    ):DistributorResponse

    @FormUrlEncoded
    @POST("/api/pengajuan-obat")
    suspend fun pengajuanObat(
        @Field("jenis_obat")jenis_obat:String,
        @Field("nama_obat")nama_obat:String,
        @Field("dosis_obat")dosis_obat:String,
        @Field("jumlah_obat")jumlah_obat:Int,
        @Field("satuan_obat")satuan_obat:String,
        @Field("distributor_id")distributor_id:Int
    ):PengajuanObatResponse

    @FormUrlEncoded
    @POST("/api/admin/show-transaksi-obat-year")
    suspend fun lihatTransaksiObat(
        @Field("tanggal_transaksi")tanggal_transaksi:String
    ):RiwayatResponse


    @FormUrlEncoded
    @POST("/api/admin/transaksi-obat")
    suspend fun addTransaksiObat(
        @Field("obat_id")obat_id:Int,
        @Field("satuan")satuan:String,
        @Field("jumlah")jumlah:Int,
        @Field("jenis_transaksi")jenis_transaksi:String
    )



    @FormUrlEncoded
    @POST("/api/admin/obat")
    suspend fun addObat(
        @Field("nama_obat")nama_obat:String,
        @Field("jenis_obat")jenis_obat:String,
        @Field("satuan_obat")satuan_obat:String,
        @Field("dosis_obat")dosis_obat:String,
//        @Field("jumlah_obat")jumlah_obat:String
    ):AddObatResponse

    @FormUrlEncoded
    @POST("/api/login")
    suspend fun fillOtp(@Field("otp")otp:String):LoginResponse


    @GET("/api/admin/obat")
    suspend fun getObat():ObatResponse

    @GET("obat/{type}")
    suspend fun getDetailObat(
        @Path("type")type:String
    ):ObatResponse

//    @GET("riwayat/{tahun}")
//    suspend fun getRiwayatTahun(
//        @Path("tahun")tahun:String
//    ):RiwayatResponse

    @GET("status/{proses}")
    suspend fun getStatusPengajuan(
        @Path("proses")proses:String
    ):StatusResponse
}