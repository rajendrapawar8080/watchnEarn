package com.rpsystems.watchnearn.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.views.activities.VideoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Our class extending fragment
public class OffersWallFragment extends Fragment {
    @BindView(R.id.videoOne_ID) ImageView mVideoFirst;
    @BindView(R.id.videoTwo_ID) ImageView mVideoSecond;
    @BindView(R.id.videoThree_ID)ImageView mVideoThird;
    @BindView(R.id.videoFour_ID) ImageView mVideoFour;
    @BindView(R.id.videoFive_ID) ImageView mVideoFive;
    @BindView(R.id.videoSix_ID) ImageView mVideoSix;
    private View mView;
    private InterstitialAd mInterstitialAd;

    //Overriden method onCreateView
    public OffersWallFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_payment in you classes
        mView=inflater.inflate(R.layout.fragment_offers,container,false);
        ButterKnife.bind(this,mView);
        initViews(mView);

        return mView;
    }
   private void initViews(View view){
       mVideoFirst= (ImageView)view.findViewById(R.id.videoOne_ID);

       mInterstitialAd = new InterstitialAd(getActivity());
       mInterstitialAd.setAdUnitId("ca-app-pub-4371432322602969/3249622334");
       mInterstitialAd.setAdListener(new AdListener() {
           @Override
           public void onAdClosed() {
               requestNewInterstitial();
              // beginPlayingGame();
           }
       });

       requestNewInterstitial();
   }
   @OnClick(R.id.videoOne_ID) public void displayAdd(){
       newActivity();
     /*  if (mInterstitialAd.isLoaded()) {
           mInterstitialAd.show();
       }else {
           Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
       }*/
   }
    @OnClick(R.id.videoTwo_ID) public void displayAddTwo(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.videoThree_ID) public void displayAddThree(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.videoFour_ID) public void displayAddFour(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.videoFive_ID) public void displayAddFive(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.videoSix_ID) public void displayAddSix(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
        }
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
    public void newActivity(){
        startActivity(new Intent(getActivity(), VideoActivity.class));
    }
}