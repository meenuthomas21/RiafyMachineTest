package com.vendor.sampleapplication.data.api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alnaqel.api.ApiService
import com.vendor.sampleapplication.data.repository.MainRepository
import eexpo_.appn.alnaqel.data.viewModel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelperNew) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}