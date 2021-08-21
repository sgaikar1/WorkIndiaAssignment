package com.sgaikar.workindia.dbutilities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface FoodDetailsDao {

    @Insert(onConflict = REPLACE)
    void save(List<FoodItem> foodItems);

    @Insert(onConflict = REPLACE)
    void save(FoodItem foodItem);

    @Query("DELETE FROM foodItem WHERE name NOT IN (:nameList)")
    void deleteOtherFoods(List<String> nameList);

    @Query("DELETE FROM foodItem")
    void deleteAll();

    @Query("SELECT * FROM fooditem ORDER BY price ASC")
    LiveData<List<FoodItem>> getFoodsByPrice();


    @Query("SELECT * FROM fooditem WHERE name = :name")
    LiveData<FoodItem> getFood(String name);
}
