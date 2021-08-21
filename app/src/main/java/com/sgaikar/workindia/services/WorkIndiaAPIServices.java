package com.sgaikar.workindia.services;

import com.sgaikar.workindia.dbutilities.FoodDetails;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkIndiaAPIServices {

    @GET("b6a30bb0-140f-4966-8608-1dc35fa1fadc")
    Call<FoodDetails> getFoodData();
}
