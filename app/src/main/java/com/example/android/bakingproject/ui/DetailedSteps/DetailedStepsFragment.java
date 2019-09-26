package com.example.android.bakingproject.ui.DetailedSteps;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.android.bakingproject.R;
import com.example.android.bakingproject.data.pojo.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.gson.Gson;

import timber.log.Timber;

public class DetailedStepsFragment extends Fragment  {
    private static final String ONE_PASSED_STEP_KEY = "passed_step_key";
    private static final String DISH_NAME_KEY = "dish_name";
    private PlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private ImageView mThumbnailImageView;
    private TextView mDescription;
    private TextView mShortDescription;
    private Steps mSteps;
    private String mDishTilte;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_steps_detail,container,false);

        mSteps = new Gson().fromJson(getArguments().getString(ONE_PASSED_STEP_KEY),Steps.class);
        mDishTilte = getArguments().getString(DISH_NAME_KEY);

        initAndSetConstViews(v);
        defineUsingVideoViewOrImageView(v);

        return v;
    }

    private void defineUsingVideoViewOrImageView(View view) {

        if (isVideoUrlNull())
        {
            initVideoPlayer(view);
            return;
        }
        if (!(mSteps.getThumbnailURL().contentEquals("")))
        {
            removeVideoViewAndAddImageView(view);
            return;
        }

            ViewGroup viewGroup = view.findViewById(R.id.steps_group_view);
            viewGroup.removeView(view.findViewById(R.id.player_view));

            View decorTextView = view.findViewById(R.id.included_text);
            decorTextView.setVisibility(View.VISIBLE);

            TextView firstLetterTextView = view.findViewById(R.id.first_letter);
            TextView firstWordTextView = view.findViewById(R.id.first_word);
            TextView secondWordTextView = view.findViewById(R.id.second_word);

            boolean isItACombinedWord = mDishTilte.contains(" ");
            firstLetterTextView.setText(String.valueOf(mDishTilte.charAt(0)));

            if (isItACombinedWord){
                int spaceIndex = mDishTilte.indexOf(' ');
                firstWordTextView.setText(mDishTilte.substring(1,spaceIndex));
                secondWordTextView.setText(mDishTilte.substring(spaceIndex));
            }else {
                firstWordTextView.setText(mDishTilte.substring(1));
            }



    }

    private boolean isVideoUrlNull() {
        return !(mSteps.getVideoURL().contentEquals(""));
    }

    private void removeVideoViewAndAddImageView(View view) {
        PlayerView playerView = view.findViewById(R.id.player_view);
        ViewGroup viewGroup =  view.findViewById(R.id.steps_group_view);
        int index = viewGroup.indexOfChild(playerView);
        viewGroup.removeView(playerView);
        mThumbnailImageView = new ImageView(getContext());
        mThumbnailImageView.setAdjustViewBounds(true);
        viewGroup.addView(mThumbnailImageView,index);
        Glide.with(getContext()).load(mSteps.getThumbnailURL()).into(mThumbnailImageView);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //TODO: fix the loading problem
        if (isVisibleToUser) {
            if (mSteps != null)
            {
                if (isVideoUrlNull())
                {
                    startVideoPlayingIfThereOne();
                }
            }

        }else{
          releasePlayer();
        }
    }

    private void startVideoPlayingIfThereOne() {
        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext()
                ,new DefaultTrackSelector());

        mPlayerView.setPlayer(mPlayer);

        DefaultDataSourceFactory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(),"demo"));

        ExtractorMediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(mSteps.getVideoURL()));

        mPlayer.prepare(mediaSource);
        mPlayer.setPlayWhenReady(true);

    }

    private void initVideoPlayer(View v) {
        mPlayerView = v.findViewById(R.id.player_view);
        startVideoPlayingIfThereOne();
    }
    //those are the view that happen to appear in every scenario
    private void initAndSetConstViews(View v) {
        mDescription = v.findViewById(R.id.description);
        mShortDescription = v.findViewById(R.id.short_description_text);
        mShortDescription.setText(mSteps.getShortDescription());
        mDescription.setText(mSteps.getDescription());
    }

    public static DetailedStepsFragment newInstance(String passedJsonStep,String passedDishName) {

        Bundle args = new Bundle();
        args.putString(ONE_PASSED_STEP_KEY,passedJsonStep);
        args.putString(DISH_NAME_KEY,passedDishName);
        DetailedStepsFragment fragment = new DetailedStepsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (mPlayerView!= null&&mPlayer != null)
        {
            mPlayerView.setPlayer(null);
            mPlayer.release();
            mPlayer = null;
        }
    }

}
