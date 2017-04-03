package com.rpsystems.watchnearn.views.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.adcolony.sdk.AdColony;
import com.adcolony.sdk.AdColonyInterstitial;
import com.adcolony.sdk.AdColonyInterstitialListener;
import com.chartboost.sdk.CBLocation;
import com.chartboost.sdk.Chartboost;
import com.google.ads.mediation.chartboost.ChartboostAdapter;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.jirbo.adcolony.AdColonyAdapter;
import com.jirbo.adcolony.AdColonyBundleBuilder;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.constants.CommonConstant;
import com.rpsystems.watchnearn.utilities.CustomObjects;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;

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
    private ProgressDialog progressDialog;
    private InterstitialAd mInterstitialAd;
    private InterstitialAd mGoogleInterestatialAds;
    private AdColonyInterstitial addColonyAdds;
    private boolean isAddLoaded=false;
    private boolean isAddFailedToLoad=false;
    private boolean isAddClosed=false;
    private boolean isAddLeftApplication=false;
    private String app_id ;
    final VunglePub vunglePub = VunglePub.getInstance();
    //Overriden method onCreateView
    public OffersWallFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_offers,container,false);
        app_id= getString(R.string.vungle_app_id);
        vunglePub.init(getActivity(), app_id);
        Chartboost.startWithAppId(getActivity(), CommonConstant.CHARTBOOST_APP_ID, CommonConstant.CHARTBOOST_SIGNATURE_ID);
        Chartboost.onCreate(getActivity());
        ButterKnife.bind(this,mView);
        initViews(mView);
        return mView;
    }
   private void initViews(View view){
       mVideoFirst= (ImageView)view.findViewById(R.id.videoOne_ID);
       progressDialog=new ProgressDialog(getActivity());
       progressDialog.setTitle("Ad is loding please wait...");
       progressDialog.setCanceledOnTouchOutside(false);
       mInterstitialAd = new InterstitialAd(getActivity());
       mInterstitialAd.setAdUnitId("ca-app-pub-4371432322602969/3792849138");

       mGoogleInterestatialAds = new InterstitialAd(getActivity());
       mGoogleInterestatialAds.setAdUnitId("ca-app-pub-4371432322602969/3249622334");
       AdColony.configure(getActivity(),CommonConstant.ADCOLONY_APP_ID,CommonConstant.ADCOLONY_ZONE_ID);

       setHandler();

   }
    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestGoogleInterstitial();
                if (mGoogleInterestatialAds.isLoaded()){
                    mGoogleInterestatialAds.show();
                }
            }
        },20000);
    }

    private void setInterestatialAddsListener(){
      mInterstitialAd.setAdListener(new AdListener() {
          @Override
          public void onAdClosed() {
              super.onAdClosed();
              isAddClosed=true;
              progressDialog.dismiss();
          }

          @Override
          public void onAdFailedToLoad(int i) {
              super.onAdFailedToLoad(i);
              isAddFailedToLoad=true;
              progressDialog.dismiss();
          }

          @Override
          public void onAdLeftApplication() {
              super.onAdLeftApplication();
              isAddLeftApplication=true;
              progressDialog.dismiss();
          }

          @Override
          public void onAdOpened() {
              super.onAdOpened();

          }

          @Override
          public void onAdLoaded() {
              super.onAdLoaded();
              progressDialog.dismiss();
              mInterstitialAd.show();
              isAddLoaded=true;
          }
      });

  }
   private void checkAddColonyAdds(){
       progressDialog.show();
       final AdColonyInterstitialListener listener = new AdColonyInterstitialListener() {
           @Override
           public void onRequestFilled(AdColonyInterstitial ad) {
               /** Store and use this ad object to show your ad when appropriate */
               ad.show();
               progressDialog.dismiss();
           }
       };
       AdColony.requestInterstitial("vz43df28276a3b4077bb", listener);
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
       showVungle();
   }
    @OnClick(R.id.videoTwo_ID) public void displayAddTwo(){
        checkAddColonyAdds();
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

        requestNewInterstitial();
        setInterestatialAddsListener();
        if (mInterstitialAd.isLoaded()){
            progressDialog.dismiss();
            mInterstitialAd.show();

        }
        if (mInterstitialAd.isLoading()){
            progressDialog.show();
        }
        if (isAddFailedToLoad||isAddLeftApplication){
            requestNewInterstitial();
            setInterestatialAddsListener();
            if (mInterstitialAd.isLoaded()){
                progressDialog.dismiss();
                mInterstitialAd.show();

            }
            if (mInterstitialAd.isLoading()){
                progressDialog.show();
            }
        }

    }
    private void showAddColony(){
    AdColonyBundleBuilder.setZoneId(CommonConstant.ADCOLONY_ZONE_ID);
    AdRequest adRequest = new AdRequest.Builder()
            .addNetworkExtrasBundle(AdColonyAdapter.class,AdColonyBundleBuilder.build())
            .build();
    mInterstitialAd.loadAd(adRequest);
    setInterestatialAddsListener();
    if (mInterstitialAd.isLoaded()){
        mInterstitialAd.show();
        progressDialog.dismiss();
    }
    if (mInterstitialAd.isLoading()){
        Toast.makeText(getActivity(), "Ad is loading....please wait", Toast.LENGTH_SHORT).show();
        progressDialog.show();
    }
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
    setInterestatialAddsListener();
    if (mInterstitialAd.isLoaded()){
        mInterstitialAd.show();
        progressDialog.dismiss();
    }
    if (mInterstitialAd.isLoading()){
        Toast.makeText(getActivity(), "Ad is loading....please wait", Toast.LENGTH_SHORT).show();
        progressDialog.show();
    }
}
    /**
     * to show vungle adds
     *
     */
private void showVungle() {
    vunglePub.addEventListeners(new EventListener() {
        @Override
        public void onAdEnd(boolean b, boolean b1) {
            progressDialog.dismiss();
        }

        @Override
        public void onAdStart() {

        }

        @Override
        public void onAdUnavailable(String s) {
            progressDialog.dismiss();
        }

        @Override
        public void onAdPlayableChanged(boolean b) {

        }

        @Override
        public void onVideoView(boolean b, int i, int i1) {

        }
    });

    if (vunglePub.isAdPlayable()) {
        progressDialog.dismiss();
        vunglePub.playAd();
    } else{
        progressDialog.show();
    }
}

    /**
     * to request addmob adds
     */
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
            mInterstitialAd.loadAd(adRequest);
    }

    /**
     * to request seperate adds through seperate instance
     */
    private void requestGoogleInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mGoogleInterestatialAds.loadAd(adRequest);

    }

}