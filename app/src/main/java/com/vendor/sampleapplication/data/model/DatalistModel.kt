package com.vendor.sampleapplication.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DatalistModel (
    var id: Int? = null,
    var title: String = "",
    var description: String = "",
    var created_at: String = "",
    var updated_at: String = ""


)

