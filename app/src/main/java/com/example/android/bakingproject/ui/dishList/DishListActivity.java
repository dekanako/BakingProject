package com.example.android.bakingproject.ui.dishList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;


import com.example.android.bakingproject.BuildConfig;
import com.example.android.bakingproject.R;

import com.example.android.bakingproject.ui.dishList.viewmodels.DishListViewModel;



import timber.log.Timber;

public class DishListActivity extends AppCompatActivity {
    private static final String TAG = "DishListActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dish);
        initTimberLogging();
        mRecyclerView = findViewById(R.id.dish_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        DishListViewModel dishListViewModel = ViewModelProviders.of(this).get(DishListViewModel.class);
        dishListViewModel.getDishes()
                .observe(this,dishes ->{
                    mRecyclerView.setAdapter(new DishListAdapter(dishes,this));
                    Timber.d("size"+dishes.get(2).getSteps().get(2).getVideoURL());
                });

    }

    private void initTimberLogging() {
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
