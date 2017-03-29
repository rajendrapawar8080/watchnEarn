package com.rpsystems.watchnearn.views.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.rpsystems.watchnearn.utilities.CustomObjects;
import com.rpsystems.watchnearn.views.activities.VideoActivity;
import com.rpsystems.watchnearn.views.activities.VungleAdds;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePub;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

//Our class extending fragment
public class OffersWallFragment extends Fragment {
    private String Tag="OffersWallFragment";
    @BindView(R.id.videoOne_ID) ImageView mVideoFirst;
    @BindView(R.id.videoTwo_ID) ImageView mVideoSecond;
    @BindView(R.id.videoThree_ID)ImageView mVideoThird;
    @BindView(R.id.videoFour_ID) ImageView mVideoFour;
    @BindView(R.id.videoFive_ID) ImageView mVideoFive;
    @BindView(R.id.videoSix_ID) ImageView mVideoSix;
    private View mView;
    private Handler mHandler;
    private AdConfig overrideConfig;



    final VunglePub vunglePub = VunglePub.getInstance();
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
        setHandler();
        return mView;
    }
   private void initViews(View view){
       mVideoFirst= (ImageView)view.findViewById(R.id.videoOne_ID);
   }
   public void setHandler(){
        mHandler = new Handler(Looper.getMainLooper()) {
           @Override
           public void handleMessage(Message message) {
               // This is where you do your work in the UI thread.
               // Your worker tells you in the message what to do.
               CustomObjects customObjects= (CustomObjects) message.obj;
               if (customObjects.isVideoCompleted()){
                   Toast.makeText(getActivity(), "You earned 1 coin", Toast.LENGTH_SHORT).show();
               }


           }
       };
   }
   @OnClick(R.id.videoOne_ID) public void displayAdd(){
       newActivity();
   }
    @OnClick(R.id.videoTwo_ID) public void displayAddTwo(){
        newActivity();
    }
    @OnClick(R.id.videoThree_ID) public void displayAddThree(){
        newActivity();
    }
    @OnClick(R.id.videoFour_ID) public void displayAddFour(){
        newActivity();
    }
    @OnClick(R.id.videoFive_ID) public void displayAddFive(){
        newActivity();
    }
    @OnClick(R.id.videoSix_ID) public void displayAddSix(){
        newActivity();
    }
    public void newActivity(){
        final String app_id = getString(R.string.vungle_app_id);

        // initialize the Publisher SDK
        vunglePub.init(getActivity(), app_id);
        PlayAdIncentivized();
       setCallBack();
    }
    private void PlayAdOptions() {
        // create a new AdConfig object
        final AdConfig overrideConfig = new AdConfig();

        // set any configuration options you like.
        overrideConfig.setOrientation(Orientation.matchVideo);
        overrideConfig.setSoundEnabled(false);
        overrideConfig.setBackButtonImmediatelyEnabled(false);
        overrideConfig.setPlacement("StoreFront");
        //overrideConfig.setExtra1("LaunchedFromNotification");

        // the overrideConfig object will only affect this ad play.
        vunglePub.playAd(overrideConfig);
    }

    private void PlayAdIncentivized() {
        // create a new AdConfig object
        final AdConfig overrideConfig = new AdConfig();

        // set incentivized option on
        overrideConfig.setIncentivized(true);
        overrideConfig.setIncentivizedCancelDialogTitle("Careful!");
        overrideConfig.setIncentivizedCancelDialogBodyText("If the video isn't completed you won't get your reward! Are you sure you want to close early?");
        overrideConfig.setIncentivizedCancelDialogCloseButtonText("Close");
        overrideConfig.setIncentivizedCancelDialogKeepWatchingButtonText("Keep Watching");

        // the overrideConfig object will only affect this ad play.
           if (vunglePub.isAdPlayable()){
           vunglePub.playAd(overrideConfig);
        }else{
            Toast.makeText(getActivity(), "add is not availbale..try again ", Toast.LENGTH_SHORT).show();
        }

    }
    public void setCallBack(){

    vunglePub.addEventListeners(new EventListener() {
        @Override
        public void onAdEnd(boolean wasSuccessfulView, boolean wasCallToActionClicked) {
            CustomObjects customObjects=new CustomObjects();
            customObjects.setVideoCompleted(wasSuccessfulView);
            customObjects.setIsvideoActioClicked(wasCallToActionClicked);
            //sending the custom object to main thread using message passing mechanism through handlers
            Message message=new Message();
            message.obj=customObjects;
            mHandler.sendMessage(message);
        }

        @Override
        public void onAdStart() {
            Log.d(Tag,"onAdStart=");
        }

        @Override
        public void onAdUnavailable(String s) {
            Log.d(Tag,"onAdUnavailable="+s);
        }

        @Override
        public void onAdPlayableChanged(boolean b) {
            Log.d(Tag,"onAdPlayableChanged="+b);
        }

        @Override
        public void onVideoView(boolean isCompletedView, int i, int i1) {
            Log.d(Tag,"onVideoView="+isCompletedView);

        }
    });

}

}