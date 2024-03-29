package com.example.android.bakingproject.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingproject.utilites.AppUtil;
import com.example.android.bakingproject.R;
import com.example.android.bakingproject.utilites.TypeUtil;
import com.example.android.bakingproject.data.pojo.Ingredients;
import com.example.android.bakingproject.utilites.PrefUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

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
        //TODO: implement a empty view screen
        @Override
        public void onCreate() { }

        @Override
        public void onDataSetChanged() {
            Timber.d("DATA SET CHANGED");

            String s = PrefUtils.getPreservedIngredientInSharedPref(mContext);
            if (s!=null&&!s.equals("")) {
             mIngredients = new Gson().fromJson(s, TypeUtil.LIST_INGREDIENTS_TYPE);

            }else {
                Timber.d("NULL NULL");
                mIngredients = new ArrayList<>();
            }

        }

        @Override
        public void onDestroy() { }

        @Override
        public int getCount() {

            return mIngredients.size();

        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (mIngredients == null || mIngredients.size() == 0) return null;

            RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.widget_list_item);

            remoteViews.setTextViewText(R.id.mesure_text_view1,mIngredients.get(position).getMeasure());
            remoteViews.setTextViewText(R.id.item_text_view1, AppUtil.capitalizeFirstLetter(mIngredients.get(position).getIngredient()));
            remoteViews.setTextViewText(R.id.quantitiy1,String.valueOf(mIngredients.get(position).getQuantity()));
            Timber.d(String.valueOf(mIngredients.get(position)));
            //TODO fix the after points number
            return remoteViews;
        }

        @Override
        public RemoteViews getLoadingView() { return null; }

        @Override
        public int getViewTypeCount() { return 1; }

        @Override
        public long getItemId(int position) { return position; }

        @Override
        public boolean hasStableIds() { return true; }
    }
}
