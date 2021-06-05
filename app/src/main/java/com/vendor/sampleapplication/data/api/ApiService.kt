package com.alnaqel.api


import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.vendor.sampleapplication.data.model.DatalistModel
import com.vendor.sampleapplication.data.model.UploadParams
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("kotlintest")
    suspend fun kotlintest(): List<DatalistModel>

    @POST("kotlintest")
    suspend fun postkotlintest(@Body params: UploadParams): String

}