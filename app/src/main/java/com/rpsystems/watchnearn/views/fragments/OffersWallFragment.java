package com.rpsystems.watchnearn.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.google.ads.mediation.chartboost.ChartboostAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.constants.CommonConstant;
import com.rpsystems.watchnearn.utilities.CustomObjects;
import com.vungle.mediation.VungleAdapter;
import com.vungle.mediation.VungleExtrasBuilder;
import com.vungle.mediation.VungleInterstitialAdapter;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.Orientation;
import com.vungle.publisher.VunglePub;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.ChartboostDelegate;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private InterstitialAd mInterstitialAd;

    private AdColonyInterstitial addColonyAdds;

    private  String app_id ;
    final VunglePub vunglePub = VunglePub.getInstance();
    //Overriden method onCreateView
    public OffersWallFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_payment in you classes
        mView=inflater.inflate(R.layout.fragment_offers,container,false);
        app_id= getString(R.string.vungle_app_id);
        vunglePub.init(getActivity(), app_id);
        AdColony.configure(getActivity(),CommonConstant.ADCOLONY_APP_ID,CommonConstant.ADCOLONY_ZONE_ID);

        Chartboost.startWithAppId(getActivity(), CommonConstant.CHARTBOOST_APP_ID, CommonConstant.CHARTBOOST_SIGNATURE_ID);
        Chartboost.onCreate(getActivity());

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-4371432322602969/3792849138");
       /* mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
            }
        });*/
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
       startAdds();
   }
    @OnClick(R.id.videoTwo_ID) public void displayAddTwo(){
        startAdds();
    }
    @OnClick(R.id.videoThree_ID) public void displayAddThree(){
        startAdds();
    }
    @OnClick(R.id.videoFour_ID) public void displayAddFour(){
        startAdds();
    }
    @OnClick(R.id.videoFive_ID) public void displayAddFive(){
        startAdds();
    }
    @OnClick(R.id.videoSix_ID) public void displayAddSix(){
        startAdds();
    }
    public void startAdds(){

        if (mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }else{
            requestNewInterstitial();
            showAddColony();
            showChatboost();
            showVungle();
            if (mInterstitialAd.isLoaded()){
                mInterstitialAd.show();
            }
        }
        if (mInterstitialAd.isLoading()){
            Toast.makeText(getActivity(), "Ad did not load....please wait", Toast.LENGTH_SHORT).show();
        }
    }
private void showAddColony(){
    AdColonyBundleBuilder.setZoneId(CommonConstant.ADCOLONY_ZONE_ID);
    AdRequest adRequest = new AdRequest.Builder()
            .addNetworkExtrasBundle(AdColonyAdapter.class,AdColonyBundleBuilder.build())
            .build();
        mInterstitialAd.loadAd(adRequest);
}
private void showChatboost(){
    Chartboost.showInterstitial(CBLocation.LOCATION_DEFAULT);
    Chartboost.hasRewardedVideo(CBLocation.LOCATION_DEFAULT);

    Bundle bundle = new ChartboostAdapter.ChartboostExtrasBundleBuilder()
            .build();
    AdRequest adRequest = new AdRequest.Builder()
            .addNetworkExtrasBundle(ChartboostAdapter.class,bundle)
            .build();

        mInterstitialAd.loadAd(adRequest);



}
private void showVungle(){
    // build network extras bundle
    Bundle extras = new VungleExtrasBuilder()
            .setUserId("userId")
            .setSoundEnabled(false)
            .build();
    AdRequest adRequest = new AdRequest.Builder()
            .addNetworkExtrasBundle(VungleInterstitialAdapter.class, extras)
            .build();
        mInterstitialAd.loadAd(adRequest);

}
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
            mInterstitialAd.loadAd(adRequest);

    }
}