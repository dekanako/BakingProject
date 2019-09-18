package com.example.android.bakingproject.ui.hosting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.POJOS.Dish;
import com.example.android.bakingproject.ui.ingredeintss.IngredientsListFragment;
import com.example.android.bakingproject.ui.steps.StepsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import timber.log.Timber;

public class HostingActivity extends AppCompatActivity {
    private String mPassedJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosting_activity);
        mPassedJson = getIntent().getStringExtra(Intent.EXTRA_INTENT);
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(new Gson().fromJson(mPassedJson, Dish.class).getName());

    }

    public  class MyPagerAdapter extends FragmentPagerAdapter {
        private  int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IngredientsListFragment.newInstance(extractedIngredients(extractDish()));
                case 1:
                    Timber.d(extractedSteps(extractDish()));
                    return StepsFragment.newInstance(extractedSteps(extractDish()),extractDish().getName());
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position)
            {
                case 0:
                    return "Ingredients";
                case 1:
                    return "Steps";
                    default:
                        return null;
            }

        }

    }

    private Dish extractDish() {
        Dish dish = new Gson().fromJson(mPassedJson,Dish.class);
        return dish;
    }

    private static String extractedSteps(Dish dish){
        return new Gson().toJson(dish.getSteps());
    }

    private static String extractedIngredients(Dish dish){
        return new Gson().toJson(dish.getIngredients());
    }
}
