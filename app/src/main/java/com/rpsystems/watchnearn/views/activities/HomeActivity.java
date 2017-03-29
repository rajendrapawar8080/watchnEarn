package com.rpsystems.watchnearn.views.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.constants.CommonConstant;
import com.rpsystems.watchnearn.controllers.adapters.Pager;
import com.rpsystems.watchnearn.controllers.interfaces.NetworkReceiver;
import com.rpsystems.watchnearn.rest.NetworkCall;
import com.rpsystems.watchnearn.rest.model.requestpojos.PaymentRequest;
import com.rpsystems.watchnearn.views.fragments.OffersWallFragment;
import com.rpsystems.watchnearn.views.fragments.PaymentFragment;
import com.rpsystems.watchnearn.views.fragments.Tab3;
import com.rpsystems.watchnearn.views.fragments.Tab4;
import com.rpsystems.watchnearn.views.fragments.Tab5;
import com.rpsystems.watchnearn.views.fragments.Tab6;
import com.rpsystems.watchnearn.views.fragments.Tab7;
import com.vungle.publisher.VunglePub;

public class HomeActivity extends AppCompatActivity implements NetworkReceiver{

    //This is our tablayout
    private TabLayout tabLayout;
 //   private InterstitialAd mInterstitialAd;
    //This is our viewPager
    private ViewPager viewPager;
    FloatingActionButton fab;
    final VunglePub vunglePub = VunglePub.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        final String app_id = getString(R.string.vungle_app_id);

        // initialize the Publisher SDK
        vunglePub.init(this, app_id);
         fab = (FloatingActionButton) findViewById(R.id.fab);


      /*  mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-4371432322602969/3249622334");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // beginPlayingGame();
            }
        });
        requestNewInterstitial();*/


      //  initListener();

        initTabValues();
        NetworkCall networkCall=new NetworkCall(this,this);
    //    networkCall.fetchWSCall();
        //Adding onTabSelectedListener to swipe views

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        Fragment tab1 = new OffersWallFragment();
                       chageFragment(tab1,"offers");
                        break;
                    case 1:
                        Fragment tab2 = new PaymentFragment();
                        chageFragment(tab2,"payment");
                        break;
                    case 2:
                        Fragment tab3 = new Tab3();
                        chageFragment(tab3,"tab3");
                        break;
                    case 3:
                        Tab4 tab4 = new Tab4();
                        chageFragment(tab4,"tab4");
                        break;
                    case 4:
                        Tab5 tab5 = new Tab5();
                        chageFragment(tab5,"tab5");
                        break;
                    case 5:
                        Tab6 tab6 = new Tab6();
                        chageFragment(tab6,"tab6");
                        break;
                    case 6:
                        Tab7 tab7 = new Tab7();
                        chageFragment(tab7,"tab7");
                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
   /* private void initListener(){
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }else {
                    Toast.makeText(HomeActivity.this, "Ad did not load", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }*/
    private void initTabValues(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.facbook));
        tabLayout.addTab(tabLayout.newTab().setText("PaymentFragment"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab3"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab4"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab5"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab6"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab7"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
    }
    private void chageFragment(Fragment fragment, String tag) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment,tag).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public <T> void onResponse(T obj, int tag) {
        PaymentRequest paymentRequest;
        paymentRequest= (PaymentRequest) obj;
        switch (tag){
            case CommonConstant.TAG_COUNTRY:

              /*  for(final Result resultObj : paymentRequest.getRestResponse().getResult()) {
                    mCardAdapter.addData(resultObj);
                    mProgressBar.setVisibility(View.GONE);
                }*/
                break;
            default:
                break;
        }
    }

    @Override
    public void onError( String msg) {

        Toast.makeText(this, "Exception=>"+msg, Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onPause() {
        super.onPause();
        vunglePub.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        vunglePub.onResume();
    }
}
