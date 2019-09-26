package com.example.android.bakingproject.ui.steps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.bakingproject.R;
import com.example.android.bakingproject.TabletUtil;
import com.example.android.bakingproject.data.pojo.Steps;
import com.example.android.bakingproject.ui.DetailedSteps.DetailedStepsActivity;
import com.example.android.bakingproject.ui.DetailedSteps.DetailedStepsFragment;
import com.google.gson.Gson;


import java.util.List;

import timber.log.Timber;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {
    private List<Steps> mSteps;
    private Context mContext;
    private String mDishName;
    private TabletClickingListener mTabletClickingListener;

    public StepsAdapter(List<Steps> Steps, Context context,String dishName,TabletClickingListener m) {
        mSteps = Steps;
        mContext = context;
        mDishName = dishName;
        mTabletClickingListener = m;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.steps_list_item,parent,false);
        return new StepsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder holder, int position) {
        holder.stepTitle.setText(mSteps.get(position).getShortDescription());
    }

    @Override
    public int getItemCount() {
        return mSteps.size();
    }

    public class StepsViewHolder extends RecyclerView.ViewHolder{
        private TextView stepTitle;
        public StepsViewHolder(@NonNull View itemView) {
            super(itemView);
            stepTitle = itemView.findViewById(R.id.short_instruction);

            if (!TabletUtil.isItATabletLayout)
            {
                stepTitle.setOnClickListener(v -> {
                    Intent intent = DetailedStepsActivity.newIntent(mContext,getAdapterPosition(),new Gson().toJson(mSteps),mDishName);

                    mContext.startActivity(intent);
                    Timber.d("phone");
                });

            } else {
                stepTitle.setOnClickListener(v -> {
                    mTabletClickingListener.onClick(getAdapterPosition());
                    Timber.d("TABLET");
                });
            }
        }
    }

    public interface TabletClickingListener{
        void onClick(int position);
    }

    public void setTabletClickingListener(TabletClickingListener tabletClickingListener) {
        mTabletClickingListener = tabletClickingListener;
    }
}
