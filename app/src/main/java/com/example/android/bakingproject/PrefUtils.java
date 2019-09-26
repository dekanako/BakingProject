package com.example.android.bakingproject;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.android.bakingproject.widget.IngredientsWidget;

public final class PrefUtils {

    private static final String INGREDIENTS_KEY_SHARED_PREF = "ingredient_shared_pref_key";
    private static final String DISH_TITLE_KEY_SHARED_PREF = "dish_title_shared_pref_key";

    /**
     * listeners are used here to avoid updating the widget for the same ingredient
     * listeners are user here to avoid updating the widget before inserting the data to the shared pref
     *
     * @param context used to access the shared pref
     * @param passedIngredientsInJson passing the ingredients as a json
     */
    public static void preserveIngredientsInSharedPref(Context context, String passedIngredientsInJson,String dishTitle){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        sharedPreferences.registerOnSharedPreferenceChangeListener((sharedPreferences1, key) ->
                updateWidget(context));
        
        sharedPreferences.edit().putString(INGREDIENTS_KEY_SHARED_PREF,passedIngredientsInJson).apply();
        sharedPreferences.edit().putString(DISH_TITLE_KEY_SHARED_PREF,dishTitle).apply();
    }

    //used to sendBroadcast to the widget to update itself
    private static void updateWidget(Context context) {
        Intent intent = new Intent(context, IngredientsWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] ids = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, IngredientsWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);

        context.sendBroadcast(intent);
    }

    public static String getPreservedIngredientInSharedPref(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(INGREDIENTS_KEY_SHARED_PREF,null);
    }
    public static String getPreservedDishTitleInSharedPref(Context context){

        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(DISH_TITLE_KEY_SHARED_PREF,null);
    }
}
