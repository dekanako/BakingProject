package com.example.android.bakingproject.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingproject.data.pojo.Ingredients;
import com.google.gson.Gson;

import java.util.List;

public class ListRemoteViewService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteViewFactory(getApplicationContext());
    }

    class RemoteViewFactory implements RemoteViewsFactory{
        private Context mContext;
        private List<Ingredients> mIngredients;
        public RemoteViewFactory(Context context) {
            mContext = context;
        }

        @Override
        public void onCreate() { }

        @Override
        public void onDataSetChanged() {
            mIngredients = new Gson().fromJson()
        }

        @Override
        public void onDestroy() { }

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            return null;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 0;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}
