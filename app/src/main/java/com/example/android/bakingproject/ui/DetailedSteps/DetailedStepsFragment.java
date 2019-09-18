package com.example.android.bakingproject.ui.DetailedSteps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.bakingproject.R;

public class DetailedStepsFragment extends Fragment {
    private static final String ONE_PASSED_STEP_KEY = "passed_step_key";
    public static DetailedStepsFragment newInstance(String passedJsonStep) {

        Bundle args = new Bundle();
        args.putString(ONE_PASSED_STEP_KEY,passedJsonStep);
        DetailedStepsFragment fragment = new DetailedStepsFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_steps_detail,container,false);
        TextView view = v.findViewById(R.id.text);
        view.setText(getArguments().getString(ONE_PASSED_STEP_KEY));
        return v;
    }
}
