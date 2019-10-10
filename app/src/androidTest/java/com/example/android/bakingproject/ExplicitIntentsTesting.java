package com.example.android.bakingproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.intent.matcher.IntentMatchers.hasPackage;
import static androidx.test.ext.truth.content.IntentSubject.assertThat;
import com.example.android.bakingproject.ui.DetailedSteps.DetailedStepsActivity;
import com.example.android.bakingproject.ui.main.DishListActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtras;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;



@RunWith(AndroidJUnit4.class)
public class ExplicitIntentsTesting {
    private static final String DISHLIST_ACTIVITY_INTENT = Intent.EXTRA_INTENT;


    @Rule
    public IntentsTestRule<DishListActivity> mTestRule
            = new IntentsTestRule<>(DishListActivity.class);


    /**
     *  intents test is checking whether
     */
    @Test
    public void intentsTest(){

        onView(withId(R.id.dish_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));

        intended(hasExtraWithKey(Intent.EXTRA_INTENT));

        onView(withId(R.id.ingredient_list)).check(matches(isCompletelyDisplayed()));

        onView(withId(R.id.pager)).perform(swipeLeft());

        onView(withId(R.id.steps_list)).check(matches(isDisplayed()));

        onView(withId(R.id.steps_list)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
    }
}
