package com.example.android.bakingproject;

import com.example.android.bakingproject.data.POJOS.Dish;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BakingApi {

    String BASE_URL = "https://api.myjson.com/bins/";

    @GET("gc46p")
    Call<List<Dish>> getDishes();

}
