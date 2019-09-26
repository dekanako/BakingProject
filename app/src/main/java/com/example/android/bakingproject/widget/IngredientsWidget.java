package com.example.android.bakingproject.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.bakingproject.PrefUtils;
import com.example.android.bakingproject.R;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredints_widget);
        // Set the GridWidgetService intent to act as the adapter for the GridView
        Intent intent = new Intent(context, ListRemoteViewService.class);
        views.setRemoteAdapter(R.id.list_widget, intent);
        views.setTextViewText(R.id.dish_title, PrefUtils.getPreservedDishTitleInSharedPref(context));

        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.list_widget);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds){
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

