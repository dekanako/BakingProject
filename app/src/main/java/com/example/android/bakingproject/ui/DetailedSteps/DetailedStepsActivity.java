package com.example.android.bakingproject.ui.DetailedSteps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.utilites.TypeUtil;
import com.example.android.bakingproject.data.pojo.Steps;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;


import java.util.List;

public class DetailedStepsActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private FragmentStatePagerAdapter mPagerAdapter;
    private List<Steps> mSteps;
    private static final String STEP_POSITION_KEY = "step_position";
    private static final String EXTRACTED_JSON_KEY = "steps_json_key";
    private static final String DISH_NAME_KEY = "dish_name_key";


    public static Intent newIntent(Context context, int stepPosition, String extractedStepsJson,String dishName) {

        Intent intent = new Intent(context,DetailedStepsActivity.class);
        intent.putExtra(STEP_POSITION_KEY,stepPosition);
        intent.putExtra(EXTRACTED_JSON_KEY,extractedStepsJson);
        intent.putExtra(DISH_NAME_KEY,dishName);
        return intent;
    }
    private String mDishTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_steps);

        mDishTitle = getIntent().getStringExtra(DISH_NAME_KEY);

        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle(mDishTitle);

        if (getIntent().hasExtra(EXTRACTED_JSON_KEY)){
            mSteps = new Gson().fromJson(getIntent().getStringExtra(EXTRACTED_JSON_KEY), TypeUtil.LIST_STEPS_TYPE);
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewPager = findViewById(R.id.steps_pager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FragmentManager fragmentManager = getSupportFragmentManager();

        initAdapter(fragmentManager);

        mViewPager.setAdapter(mPagerAdapter);

        //takes you to the selected Step which selected in the HostingActivity
        mViewPager.setCurrentItem(getIntent().getIntExtra(STEP_POSITION_KEY,0));

    }



    private void initAdapter(FragmentManager fragmentManager) {
        mPagerAdapter = new FragmentStatePagerAdapter(fragmentManager) {

            @Override
            public Fragment getItem(int position) { return DetailedStepsFragment.newInstance(new Gson().toJson(mSteps.get(position)),mDishTitle); }

            @Override
            public int getCount() { return mSteps.size(); }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return "Step " + (position+1);
            }
        };
    }
}
