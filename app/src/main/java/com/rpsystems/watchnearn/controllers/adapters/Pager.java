package com.rpsystems.watchnearn.controllers.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.views.fragments.DailyBonusFragment;
import com.rpsystems.watchnearn.views.fragments.FunnyVideosFragment;
import com.rpsystems.watchnearn.views.fragments.InviteFragment;
import com.rpsystems.watchnearn.views.fragments.OffersWallFragment;
import com.rpsystems.watchnearn.views.fragments.PaymentFragment;
import com.rpsystems.watchnearn.views.fragments.PlansFragment;
import com.rpsystems.watchnearn.views.fragments.ProfileFragment;
import com.rpsystems.watchnearn.views.fragments.SupportFragment;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    Context context;
    FragmentManager fragmentManager;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        fragmentManager=fm;
        //Initializing tab count
        this.tabCount= tabCount;

    }
    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                OffersWallFragment tab1 = new OffersWallFragment();
                Log.d("Pager","BackStack for fragments="+fragmentManager.getBackStackEntryCount());
            //    chageFragment(tab1);
                return tab1;
            case 1:
                FunnyVideosFragment tab2 = new FunnyVideosFragment();
                Log.d("Pager","BackStack for fragments="+fragmentManager.getBackStackEntryCount());
             //   chageFragment(tab2);
                return tab2;
            case 2:
                DailyBonusFragment tab3 = new DailyBonusFragment();
               return tab3;

            case 3:
                PaymentFragment tab4 = new PaymentFragment();
                Log.d("Pager","BackStack for fragments="+fragmentManager.getBackStackEntryCount());
             //   chageFragment(tab3);
                return tab4;
            case 4:
                PlansFragment tab5 = new PlansFragment();
             //   chageFragment(tab4);
                return tab5;
            case 5:
                InviteFragment tab6 = new InviteFragment();
             //   chageFragment(tab5);
                return tab6;
            case 6:
                SupportFragment tab7 = new SupportFragment();
               // chageFragment(tab6);
                return tab7;
            case 7:
                ProfileFragment tab8 = new ProfileFragment();
              //  chageFragment(fragment_dailybonus);
                return tab8;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
    private void chageFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content,fragment).disallowAddToBackStack().commit();
    }


}