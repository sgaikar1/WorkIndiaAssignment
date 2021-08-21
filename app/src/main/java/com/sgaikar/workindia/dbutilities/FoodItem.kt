package com.sgaikar.workindia.dbutilities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

@Entity
class FoodItem {
    @PrimaryKey
    @SerializedName("name")
    @Expose
    @NonNull
    var name: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

    @SerializedName("extra")
    @Expose
    var extra: String? = null
}