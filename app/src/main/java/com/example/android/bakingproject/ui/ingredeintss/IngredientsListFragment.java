package com.example.android.bakingproject.ui.ingredeintss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.pojo.Ingredients;
import com.example.android.bakingproject.ui.hosting.IngredientsAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;


public class IngredientsListFragment extends Fragment {
    private static final String INGREDIENTS_KEY = "Ingredients_key";

    public static IngredientsListFragment newInstance(String passedJson) {
        Bundle args = new Bundle();
        args.putString(INGREDIENTS_KEY,passedJson);
        IngredientsListFragment fragment = new IngredientsListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_list_ingredeints,container,false);
        mRecyclerView = view.findViewById(R.id.ingredient_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String passedString = getArguments().getString(INGREDIENTS_KEY);
        Type listType = new TypeToken<List<Ingredients>>(){}.getType();

        List<Ingredients> ingredients = new Gson().fromJson(passedString,listType);
        mRecyclerView.setAdapter(new IngredientsAdapter(ingredients));
        return view;
    }



}
