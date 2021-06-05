package com.vendor.sampleapplication.data.repository

import com.vendor.sampleapplication.data.api.ApiHelperNew
import com.vendor.sampleapplication.data.model.UploadParams


class MainRepository(val apiHelper: ApiHelperNew) {


    suspend fun loadData() =  apiHelper.getData()
    suspend fun uploadData(params: UploadParams) =  apiHelper.uploadData(params)

    /*suspend fun postData(context:Context,param: UploadParams) =  ApiHelper.safeApiCall(context = context,
        handleLoading = true,
        call = {
            ApiHelper.apiService.postkotlintest()
        }
    )*/

}