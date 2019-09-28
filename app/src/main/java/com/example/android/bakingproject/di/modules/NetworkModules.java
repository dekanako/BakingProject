package com.example.android.bakingproject.di.modules;




import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;

import com.example.android.bakingproject.BakingApi;
import com.example.android.bakingproject.BuildConfig;
import com.jakewharton.espresso.OkHttp3IdlingResource;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModules {

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient httpClient){
        return new Retrofit.Builder()
                .baseUrl(BakingApi.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(){
        OkHttpClient okHttpClient = new OkHttpClient();

        //we check to know if it's not a release version
        if (BuildConfig.DEBUG){
            //if we haven't implement the library as a debugImplementation we couldn't call this OkHttp3IdlingResource.create("OkHttp", okHttpClient);
            IdlingResource resource = OkHttp3IdlingResource.create("OkHttp", okHttpClient);
            IdlingRegistry.getInstance().register(resource);
        }
        return okHttpClient;
    }

    @Singleton
    @Provides
    public static BakingApi provideBakingApi(Retrofit retrofit){
        return retrofit.create(BakingApi.class);
    }

}
