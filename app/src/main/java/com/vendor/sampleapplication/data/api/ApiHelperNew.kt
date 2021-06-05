package com.vendor.sampleapplication.data.api

import com.alnaqel.api.ApiService
import com.vendor.sampleapplication.data.model.UploadParams

class ApiHelperNew (private val apiService: ApiService) {

    suspend fun getData() = apiService.kotlintest()
    suspend fun uploadData(params :UploadParams) = apiService.postkotlintest(params)

}