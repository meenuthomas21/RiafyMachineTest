package eexpo_.appn.alnaqel.data.viewModel

import android.content.Context
import androidx.lifecycle.*
import com.alnaqel.api.NetworkCall
import com.vendor.sampleapplication.data.api.Resource
import com.vendor.sampleapplication.data.model.UploadParams
import com.vendor.sampleapplication.data.repository.MainRepository

import kotlinx.coroutines.Dispatchers

class MainViewModel (private val mainRepo: MainRepository): ViewModel() {

   /* fun getfromcityListCrossBorder(context: Context,params:UploadParams) = liveData(Dispatchers.IO) {
        emit(mainRepo.postData(context,params))
    }*/

    fun getData() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepo.loadData()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }


    fun uploadData(params: UploadParams) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepo.uploadData(params)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    /*fun postData( param:UploadParams) = liveData(Dispatchers.IO) {
        emit(NetworkCall.loading(data = null))
        try {
            emit(NetworkCall.success(data = mainRepo.postData(param)))
        } catch (exception: Exception) {
            emit(NetworkCall.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }*/

}