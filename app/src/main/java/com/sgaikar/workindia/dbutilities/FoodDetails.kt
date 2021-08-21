package com.sgaikar.workindia.dbutilities

import androidx.room.Entity
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodDetails {
    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("error")
    @Expose
    var error: String? = null

    @SerializedName("data")
    @Expose
    var data: Data? = null
}