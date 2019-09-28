package com.example.android.bakingproject.di.components;

import com.example.android.bakingproject.di.ApplicationModule;
import com.example.android.bakingproject.di.modules.NetworkModules;
import com.example.android.bakingproject.ui.main.viewmodels.DishListViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModules.class })
public interface AppComponent {
    void inject(ApplicationModule applicationModule);
    void inject(DishListViewModel dishListViewModel);
}
