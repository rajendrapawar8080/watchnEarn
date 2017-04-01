package com.rpsystems.watchnearn.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rpsystems.watchnearn.views.fragments.InviteFragment;
import com.rpsystems.watchnearn.views.fragments.OffersWallFragment;
import com.rpsystems.watchnearn.views.fragments.PaymentFragment;
import com.rpsystems.watchnearn.views.fragments.PlansFragment;
import com.rpsystems.watchnearn.views.fragments.ProfileFragment;
import com.rpsystems.watchnearn.views.fragments.SupportFragment;
import com.rpsystems.watchnearn.views.fragments.Tab7;

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
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
                return tab1;
            case 1:
                PaymentFragment tab2 = new PaymentFragment();
                return tab2;
            case 2:
                PlansFragment tab3 = new PlansFragment();
                return tab3;
            case 3:
                InviteFragment tab4 = new InviteFragment();
                return tab4;
            case 4:
                SupportFragment tab5 = new SupportFragment();
                return tab5;
            case 5:
                ProfileFragment tab6 = new ProfileFragment();
                return tab6;
            case 6:
                Tab7 tab7 = new Tab7();
                return tab7;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}