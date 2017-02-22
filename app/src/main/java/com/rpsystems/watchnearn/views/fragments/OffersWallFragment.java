package com.rpsystems.watchnearn.views.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rpsystems.watchnearn.R;

//Our class extending fragment
public class OffersWallFragment extends Fragment {

    private VideoView mVideoFirst;
    private View mView;
    private InterstitialAd mInterstitialAd;
    TextView textView;

    //Overriden method onCreateView
    public OffersWallFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_payment in you classes
        mView=inflater.inflate(R.layout.fragmentofferswall,container,false);
        initViews(mView);
        initListener();
        return mView;
    }
   private void initViews(View view){
       mVideoFirst= (VideoView)view.findViewById(R.id.videoViewOne_Id);

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

       textView= (TextView) view.findViewById(R.id.textView);
       textView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (mInterstitialAd.isLoaded()) {
                   mInterstitialAd.show();
               }else {
                   Toast.makeText(getActivity(), "Ad did not load", Toast.LENGTH_SHORT).show();
               }
           }
       });


   }
    private void initListener(){
        mVideoFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}