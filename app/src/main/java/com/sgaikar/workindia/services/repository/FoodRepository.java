package com.sgaikar.workindia.services.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.sgaikar.workindia.dbutilities.AppDatabase;
import com.sgaikar.workindia.dbutilities.FoodDetails;
import com.sgaikar.workindia.services.APIClient;
import com.sgaikar.workindia.services.WorkIndiaAPIServices;
import com.sgaikar.workindia.worker.SaveFoodMenu;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodRepository {

    private static FoodRepository instance;
    private static final String TAG = "FoodRepository";

    private WorkIndiaAPIServices workIndiaAPIServices = APIClient.getClient().create(WorkIndiaAPIServices.class);

    public MutableLiveData<Boolean> getFoodMenu(final Context context){

        final MutableLiveData<Boolean> isFoodCallOngoing = new MutableLiveData<>();
        isFoodCallOngoing.setValue(true);

        workIndiaAPIServices.getFoodData().enqueue(new Callback<FoodDetails>() {
            @Override
            public void onResponse(Call<FoodDetails> call, Response<FoodDetails> response) {
                if(response.isSuccessful()) {
                    new SaveFoodMenu(AppDatabase.getDatabase(context), response.body()).execute();
                    isFoodCallOngoing.setValue(false);
                }else{
                    Log.e(TAG,"response not successful");
                }
            }

            @Override
            public void onFailure(Call<FoodDetails> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
        return isFoodCallOngoing;
    }

    public static FoodRepository getInstance() {
        if(instance == null){
            synchronized (FoodRepository.class){
                if(instance == null){
                    instance = new FoodRepository();
                }
            }
        }
        return instance;
    }

}
