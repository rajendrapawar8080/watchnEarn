package com.rpsystems.watchnearn.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.chartboost.sdk.Chartboost;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.constants.CommonConstant;
import com.rpsystems.watchnearn.controllers.adapters.Pager;
import com.rpsystems.watchnearn.controllers.interfaces.NetworkReceiver;
import com.rpsystems.watchnearn.rest.NetworkCall;
import com.rpsystems.watchnearn.rest.model.requestpojos.PaymentRequest;
import com.rpsystems.watchnearn.views.fragments.FunnyVideosFragment;
import com.rpsystems.watchnearn.views.fragments.InviteFragment;
import com.rpsystems.watchnearn.views.fragments.OffersWallFragment;
import com.rpsystems.watchnearn.views.fragments.PaymentFragment;

import com.rpsystems.watchnearn.views.fragments.PlansFragment;
import com.rpsystems.watchnearn.views.fragments.ProfileFragment;
import com.rpsystems.watchnearn.views.fragments.SupportFragment;
import com.rpsystems.watchnearn.views.fragments.Tab7;
import com.vungle.publisher.VunglePub;

public class HomeActivity extends AppCompatActivity implements NetworkReceiver{

    //This is our tablayout
    public static String TAG="HomeActivity";
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
         vunglePub.init(this, app_id);
         fab = (FloatingActionButton) findViewById(R.id.fab);

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
                            Fragment tab2 = new FunnyVideosFragment();
                            chageFragment(tab2,"funnyVideos");

                        break;
                    case 2:
                            Fragment tab3 = new PaymentFragment();
                            chageFragment(tab3,"payment");
                    case 3:
                            Fragment tab4 = new PlansFragment();
                            chageFragment(tab4,"plans");
                    case 4:
                            Fragment tab5 = new InviteFragment();
                            chageFragment(tab5,"invite");

                    case 5:
                            Fragment tab6 = new SupportFragment();
                            chageFragment(tab6,"support");

                    case 6:
                            Fragment tab7 = new ProfileFragment();
                            chageFragment(tab7,"profile");
                       /* ProfileFragment tab7= new ProfileFragment();
                        chageFragment(tab7,"profile");*/
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

    private void initTabValues(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("Add Offers"));
        tabLayout.addTab(tabLayout.newTab().setText("Funny Videos"));
        tabLayout.addTab(tabLayout.newTab().setText("Payment Fragment"));
        tabLayout.addTab(tabLayout.newTab().setText("Plans"));
        tabLayout.addTab(tabLayout.newTab().setText("Invite"));
        tabLayout.addTab(tabLayout.newTab().setText("Support"));
        tabLayout.addTab(tabLayout.newTab().setText("Profile"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.pager);

        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Adding adapter to pager
        viewPager.setAdapter(adapter);
    }
    private void chageFragment(Fragment fragment,String tag) {
        Log.d(TAG,"BackStack for fragments="+getSupportFragmentManager().getBackStackEntryCount());
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment).disallowAddToBackStack().commit();
    }
private void showFragment(Fragment fragment){
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.content,fragment).show(fragment).commit();
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
        Chartboost.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        vunglePub.onResume();
        Chartboost.onResume(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        Chartboost.onStop(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Chartboost.onDestroy(this);
    }

    @Override
    public void onBackPressed() {
        // If an interstitial is on screen, close it.
        if (Chartboost.onBackPressed())
            return;
        else
            super.onBackPressed();
    }

}
