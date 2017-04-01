package com.rpsystems.watchnearn.utilities;

import android.app.Service;

import java.io.Serializable;

/**
 * Created by neosoft on 29/3/17.
 */

public class CustomObjects implements Serializable {
    boolean isVideoCompleted;
    boolean isvideoActioClicked;

    public boolean isVideoCompleted() {
        return isVideoCompleted;
    }

    public void setVideoCompleted(boolean videoCompleted) {
        isVideoCompleted = videoCompleted;
    }

    public boolean isvideoActioClicked() {
        return isvideoActioClicked;
    }

    public void setIsvideoActioClicked(boolean isvideoActioClicked) {
        this.isvideoActioClicked = isvideoActioClicked;
    }
}
