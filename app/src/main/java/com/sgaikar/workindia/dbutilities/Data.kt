package com.sgaikar.workindia.dbutilities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Data {
    @SerializedName("items")
    @Expose
    var items: List<FoodItem>? = null
}