package com.sgaikar.workindia.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sgaikar.workindia.dbutilities.AppDatabase
import com.sgaikar.workindia.dbutilities.FoodItem
import com.sgaikar.workindia.services.repository.FoodRepository

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private var db: AppDatabase? = null
    private val foodDetailsMediatorLiveData: MediatorLiveData<List<FoodItem>?> =
        MediatorLiveData<List<FoodItem>?>()
    private var foodDetailsLiveDataSortPrice: LiveData<List<FoodItem>>? = null
    private var isFoodCallInProgress = MutableLiveData<Boolean>()

    private fun init() {
        db = AppDatabase.getDatabase(getApplication<Application>().applicationContext)
        updateFoodMenu()
        subscribeToFoodChanges()
    }

    private fun updateFoodMenu() {
        isFoodCallInProgress = FoodRepository.getInstance()
            .getFoodMenu(getApplication<Application>().applicationContext)
    }

    private fun subscribeToFoodChanges() {
        foodDetailsLiveDataSortPrice = db?.foodDetailsDao()?.foodsByPrice
        foodDetailsMediatorLiveData.addSource(
            foodDetailsLiveDataSortPrice!!
        ) { foodDetails -> foodDetailsMediatorLiveData.value = foodDetails }

    }

    val foodDetailsMutableLiveData: MediatorLiveData<List<FoodItem>?>
        get() = foodDetailsMediatorLiveData


    val isFoodUpdateInProgress: LiveData<Boolean>
        get() = isFoodCallInProgress

    init {
        init()
    }
}