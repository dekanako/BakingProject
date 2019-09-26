package com.example.android.bakingproject.ui.hosting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.bakingproject.PrefUtils;
import com.example.android.bakingproject.R;
import com.example.android.bakingproject.TabletUtil;
import com.example.android.bakingproject.data.pojo.Dish;
import com.example.android.bakingproject.data.pojo.Steps;
import com.example.android.bakingproject.ui.DetailedSteps.DetailedStepsFragment;
import com.example.android.bakingproject.ui.ingredeintss.IngredientsListFragment;
import com.example.android.bakingproject.ui.steps.StepsAdapter;
import com.example.android.bakingproject.ui.steps.StepsFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import timber.log.Timber;

public class HostingActivity extends AppCompatActivity implements StepsAdapter.TabletClickingListener {
    private String mPassedJson;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hosting_activity);

        mContext = this;

        mPassedJson = getIntent().getStringExtra(Intent.EXTRA_INTENT);

        checkIfItsATabletLayout();

        //setting up viewpager
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        //set the tab layout as an identifier for each tab
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //initializing the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(new Gson().fromJson(mPassedJson, Dish.class).getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void checkIfItsATabletLayout() {
        if (findViewById(R.id.tablet_variation) == null){
            TabletUtil.isItATabletLayout = false;
            Timber.d("FALSE");
        }else {
            TabletUtil.isItATabletLayout = true;
            Timber.d("true");
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        String preservedString = PrefUtils.getPreservedIngredientInSharedPref(this);
        if (preservedString != null){

            if (preservedString.equals(extractedIngredients(extractDish()))){
                pinnedStateMenuItem(menu);
                return true;
            }
        }
        return super.onPrepareOptionsMenu(menu);
    }

    //change the menuItem
    private void pinnedStateMenuItem(Menu menu) {
        MenuItem menuItem = menu.findItem(R.id.pin_ingredient);
        menuItem.setIcon(getDrawable(R.drawable.ic_bookmark_black_24dp));
        menuItem.setTitle(R.string.unpinString);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hosting_activity_menu,menu);
        return true;
    }

    //im using the title of the item as an indicator to know in which state the MenuItem is
    //calling invalidateOptionMenu -> onPrepareOptionsMenu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.pin_ingredient:
                if (item.getTitle().equals(getString(R.string.unpinString))){
                    PrefUtils.preserveIngredientsInSharedPref(this,null,null);
                    invalidateOptionsMenu();
                }else if (item.getTitle().equals(getString(R.string.pinit))){
                    PrefUtils.preserveIngredientsInSharedPref(this,extractedIngredients(extractDish()),extractDish().getName());
                    invalidateOptionsMenu();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Steps steps = extractDish().getSteps().get(position);
        String stepInJson = new Gson().toJson(steps,Steps.class);

        //in the first deploy of the hosting activity there's only 2 fragments StepsFragment and IngredientsFragment
        //so the size is equal to 2 ,we use this block of if int the sake of not doing redundant calls to find view by id 😊
        if (fragmentManager.getFragments().size() == 2){
            TextView textView = findViewById(R.id.helping_text);
            textView.setVisibility(View.GONE);
            Timber.d("REMOVING HINT only and only when theres 2 fragments");
        }

        //using replace for the sake of not cashing the previous fragments in the stack
        fragmentManager.beginTransaction()
                .replace(R.id.detailes_steps_container,DetailedStepsFragment.newInstance(stepInJson))
                .commit();
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
                    if (TabletUtil.isItATabletLayout){
                        return StepsFragment.newInstance(extractedSteps(extractDish()),extractDish().getName(),(StepsAdapter.TabletClickingListener)mContext);
                    }else {
                        return StepsFragment.newInstance(extractedSteps(extractDish()),extractDish().getName());
                    }
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Ingredients";
                case 1:
                    return "Steps";
                    default:
                        return null;
            }
        }
    }

    private Dish extractDish() { return new Gson().fromJson(mPassedJson,Dish.class); }

    private static String extractedSteps(Dish dish){ return new Gson().toJson(dish.getSteps());}

    private static String extractedIngredients(Dish dish){ return new Gson().toJson(dish.getIngredients()); }
}
