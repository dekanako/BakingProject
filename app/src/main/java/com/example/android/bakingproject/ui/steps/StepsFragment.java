package com.example.android.bakingproject.ui.steps;

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
import com.example.android.bakingproject.data.pojo.Steps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class StepsFragment extends Fragment {
    private static final String STEPS_KEY = "Steps_fragment";
    private static final String DISH_NAME = "dish_name";

    public static StepsFragment newInstance(String passedJSONOfSteps,String dishName) {

        Bundle args = new Bundle();
        args.putString(STEPS_KEY,passedJSONOfSteps);
        args.putString(DISH_NAME,dishName);

        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_instruction,container,false);
        mRecyclerView = view.findViewById(R.id.steps_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Type type = new TypeToken<List<Steps>>(){}.getType();
        List<Steps> Steps = new Gson().fromJson(getArguments().getString(STEPS_KEY),type);

        mRecyclerView.setAdapter(new StepsAdapter(Steps,getContext(),getArguments().getString(DISH_NAME)));

        return view;
    }
}
