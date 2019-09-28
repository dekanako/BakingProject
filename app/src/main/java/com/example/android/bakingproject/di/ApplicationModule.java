package com.example.android.bakingproject.di;

import android.app.Application;

import com.example.android.bakingproject.di.components.AppComponent;
import com.example.android.bakingproject.di.components.DaggerAppComponent;


import javax.inject.Inject;



public class ApplicationModule extends Application {

    @Inject AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.create().inject(this);

    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
