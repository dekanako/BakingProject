package com.example.android.bakingproject;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.android.bakingproject.ui.main.DishListActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
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
    @After
    public void unreg(){

    }


//    Matcher<View> hasValueEqualTo(final String content) {
//
//        return new TypeSafeMatcher<View>() {
//
//            @Override
//            public void describeTo(Description description) {
//                description.appendText("Has EditText/TextView the value:  " + content);
//            }
//
//            @Override
//            public boolean matchesSafely(View view) {
//                if (!(view instanceof TextView) && !(view instanceof EditText)) {
//                    return false;
//                }
//                if (view != null) {
//                    String text;
//                    if (view instanceof TextView) {
//                        text = ((TextView) view).getText().toString();
//                    } else {
//                        text = ((EditText) view).getText().toString();
//                    }
//
//                    return (text.equalsIgnoreCase(content));
//                }
//                return false;
//            }
//        };
//    }

}
