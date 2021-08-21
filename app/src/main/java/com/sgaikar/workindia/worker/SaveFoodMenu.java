package com.sgaikar.workindia.worker;

import android.os.AsyncTask;

import com.sgaikar.workindia.dbutilities.AppDatabase;
import com.sgaikar.workindia.dbutilities.FoodDetails;
import com.sgaikar.workindia.dbutilities.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class SaveFoodMenu extends AsyncTask<Void, Void, Void> {
    private AppDatabase db;
    private List<FoodItem> foodItems;
    public SaveFoodMenu(AppDatabase db, FoodDetails foodDetails) {
        this.db = db;
        this.foodItems = foodDetails.getData().getItems();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        if(db!=null){
            if(foodItems !=null && foodItems.size()>0) {
                db.foodDetailsDao().save(foodItems);
            }else{
                db.foodDetailsDao().deleteAll();
            }
        }
        return null;
    }
}
