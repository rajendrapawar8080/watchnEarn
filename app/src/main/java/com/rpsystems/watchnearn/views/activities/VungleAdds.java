package com.rpsystems.watchnearn.views.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.rpsystems.watchnearn.R;
import com.vungle.publisher.VunglePub;

/**
 * Created by neosoft on 29/3/17.
 */

public class VungleAdds extends android.app.Activity {

    // get the VunglePub instance
    final VunglePub vunglePub = VunglePub.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get your App ID from the app's main page on the Vungle Dashboard after setting up your app
        final String app_id = getString(R.string.vungle_app_id);

        // initialize the Publisher SDK
        vunglePub.init(this, app_id);

        if (vunglePub.isAdPlayable()){
            vunglePub.playAd();
        }

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