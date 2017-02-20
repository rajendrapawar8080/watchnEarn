package com.rpsystems.watchnearn.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rpsystems.watchnearn.views.fragments.OffersWallFragment;
import com.rpsystems.watchnearn.views.fragments.PaymentFragment;
import com.rpsystems.watchnearn.views.fragments.Tab3;
import com.rpsystems.watchnearn.views.fragments.Tab4;
import com.rpsystems.watchnearn.views.fragments.Tab5;
import com.rpsystems.watchnearn.views.fragments.Tab6;
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
                Tab3 tab3 = new Tab3();
                return tab3;
            case 3:
                Tab4 tab4 = new Tab4();
                return tab4;
            case 4:
                Tab5 tab5 = new Tab5();
                return tab5;
            case 5:
                Tab6 tab6 = new Tab6();
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