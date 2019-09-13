package com.example.android.bakingproject.di.modules;

import com.example.android.bakingproject.BakingApi;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModules {

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BakingApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public static BakingApi prvoideBakingApi(Retrofit retrofit){
        return retrofit.create(BakingApi.class);
    }

}
