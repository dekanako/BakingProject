package com.example.android.bakingproject.ui.main.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.android.bakingproject.data.pojo.Dish;
import com.example.android.bakingproject.data.repository.DishesRepository;
import com.example.android.bakingproject.di.ApplicationModule;

import java.util.List;

import javax.inject.Inject;

public class DishListViewModel extends AndroidViewModel{

     private LiveData<List<Dish>> mDishes;
     @Inject DishesRepository mDishesRepository;

    public DishListViewModel(@NonNull Application application) {
        super(application);
        ((ApplicationModule)getApplication()).getAppComponent().inject(this);
        mDishes = mDishesRepository.getDishesList();
    }


    public LiveData<List<Dish>> getDishes() {
        return mDishes;
    }
}
