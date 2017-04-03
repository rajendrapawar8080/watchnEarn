package com.rpsystems.watchnearn.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.views.activities.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 3/4/17.
 */

public class FunnyVideosFragment extends Fragment {
    @BindView(R.id.funny_videoOne_ID)ImageView mFirstClip;
    private View mView;

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_payment in you classes
        mView=inflater.inflate(R.layout.fragment_funnyvideos, container, false);
        ButterKnife.bind(this,mView);

        return mView;

    }
    @OnClick(R.id.funny_videoOne_ID)public void showClipFirst(){
        startActivity(new Intent(getActivity(), VideoActivity.class));
    }
}
