package com.rpsystems.watchnearn.views.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rpsystems.watchnearn.R;

//Our class extending fragment
public class Tab7 extends Fragment {

    //Overriden method onCreateView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Returning the layout file after inflating
        //Change R.layout.fragment_payment in you classes
        return inflater.inflate(R.layout.tab7, container, false);
    }
}