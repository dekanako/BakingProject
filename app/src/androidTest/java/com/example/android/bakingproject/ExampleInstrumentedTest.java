package com.example.android.bakingproject;






import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.bakingproject.ui.main.DishListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


/**
 * Instrumented ingredient_list_item, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<DishListActivity>mActivityTestRule = new ActivityTestRule<>(DishListActivity.class);

    @Test
    public void test() {

        onView(withId(R.id.dish_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));


    }
}
