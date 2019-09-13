package com.example.android.bakingproject.data.repository;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.bakingproject.BakingApi;
import com.example.android.bakingproject.data.POJOS.Dish;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

@Singleton
public class DishesRepository {
    private BakingApi mBakingApi;

    @Inject
    public DishesRepository(BakingApi bakingApi) {
        mBakingApi = bakingApi;
    }

    public LiveData<List<Dish>> getDishesList(){
        MutableLiveData<List<Dish>> mutableLiveData = new MutableLiveData<>();

        mBakingApi.getDishes().enqueue(new Callback<List<Dish>>() {
            @Override
            public void onResponse(@NotNull Call<List<Dish>> call, @NotNull Response<List<Dish>> response) {

                mutableLiveData.setValue(response.body());
                Timber.d("ADDRESS NETWORK" + this.hashCode());
                Timber.d("RESPONSE %s", response.code());
            }

            @Override
            public void onFailure(Call<List<Dish>> call, Throwable t) { Timber.e(t); }
        });
        return mutableLiveData;
    }

}
