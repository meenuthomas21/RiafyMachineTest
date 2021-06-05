package com.alnaqel.api

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*


//@JsonAdapter(ItemTypeAdapterFactory::class)
class BaseResponse<T> {
    @SerializedName("ErrorCode")
    @Expose
    var errorcode: Int? = null

    @SerializedName("Messsage")
    @Expose
    var newerrormessage: String? = null

    @SerializedName("Data")
    @Expose
    var data: T? = null
        private set

    @SerializedName("Message")
    @Expose
    var error: String? = null
//        private set

    @SerializedName("locationdata")
    @Expose
    val locationdata: ArrayList<Locationdatum> = ArrayList<Locationdatum>()

    @SerializedName("error")
    @Expose
    var message: List<String>? = null

    fun setData(data: T) {
        this.data = data
    }

}

//private class ItemTypeAdapterFactory : TypeAdapterFactory {
//    override fun <T> create(gson: Gson, type: TypeToken<T>): TypeAdapter<T> {
//        val delegate = gson.getDelegateAdapter(this, type)
//        val elementAdapter =
//            gson.getAdapter(JsonElement::class.java)
//        return object : TypeAdapter<T>() {
//            @Throws(IOException::class)
//            override fun write(out: JsonWriter?, value: T) {
//                delegate.write(out, value)
//            }
//
//            @Throws(IOException::class)
//            override fun read(inn: JsonReader?): T {
//                val jsonElement = elementAdapter.read(inn)
//                if (jsonElement.isJsonObject) {
//                    val jsonObject = jsonElement.asJsonObject
//                    if (jsonObject.get("success").asInt != 1) {
//                        if (jsonObject.get("error").isJsonPrimitive) {
//                            jsonObject.add("errorMessage", jsonObject.get("error"))
//                            jsonObject.remove("error")
//                        } else if (jsonObject.get("error").isJsonArray) {
//                            val data = jsonObject.get("error").asJsonArray
//                            data.forEach {
//                                val item = data.get(it)
//                                if (item.isJsonArray) {
//                                    if (item.asJsonArray.size() != 0) {
//                                        val el = item.asJsonArray.get(0)
//                                        if (el.isJsonPrimitive) {
//                                            jsonObject.add("errorMessage", el)
//                                            jsonObject.remove("error")
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                return delegate.fromJsonTree(jsonElement)
//            }
//        }.nullSafe()
//    }
//}

class Locationdatum() : Parcelable {
    @SerializedName("warehouse_id")
    @Expose
    var warehouseId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("zone_id")
    @Expose
    var zoneId: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("sort_order")
    @Expose
    var sortOrder: String? = null

    constructor(parcel: Parcel) : this() {
        warehouseId = parcel.readString()
        name = parcel.readString()
        zoneId = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
        address = parcel.readString()
        sortOrder = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(warehouseId)
        parcel.writeString(name)
        parcel.writeString(zoneId)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(address)
        parcel.writeString(sortOrder)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Locationdatum> {
        override fun createFromParcel(parcel: Parcel): Locationdatum {
            return Locationdatum(parcel)
        }

        override fun newArray(size: Int): Array<Locationdatum?> {
            return arrayOfNulls(size)
        }
    }
}
