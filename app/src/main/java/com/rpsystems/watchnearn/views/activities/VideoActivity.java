package com.rpsystems.watchnearn.views.activities;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.rpsystems.watchnearn.R;
import com.rpsystems.watchnearn.constants.CommonConstant;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by neosoft on 20/3/17.
 */

public class VideoActivity extends AppCompatActivity {
    public static String TAG = CommonConstant.VideoActivity;
    @BindView(R.id.videoView_ID) VideoView mVideoView;
    private InterstitialAd mInterstetialAdds;
    private boolean isAddLoaded=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);
        ButterKnife.bind(this);
        isAddLoaded=true;
        checkDevice();
        Log.d(TAG, "external storage path=" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/Sample Videos.mp4");
    }
    private void initMediaController() {
        MediaController mediaController = new MediaController(VideoActivity.this);
        mediaController.setAnchorView(mVideoView);
        Uri uri = Uri.parse(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/Sample Videos.mp4");
        mVideoView.setMediaController(mediaController);
        mVideoView.setVideoURI(uri);
        mVideoView.requestFocus();
        mVideoView.start();
        Log.d(TAG, "external storage path=" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/Sample Videos.mp4");
       mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               mVideoView.stopPlayback();
               finish();
           }
       });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initAdds();
            }
        },500);
    }
    private void checkDevice(){
        if (Build.VERSION.SDK_INT>=android.os.Build.VERSION_CODES.M){
            checkPermission();
        }else{
            initMediaController();

        }
    }
    private void initAdds(){
        mInterstetialAdds=new InterstitialAd(VideoActivity.this);
        mInterstetialAdds.setAdUnitId("ca-app-pub-4371432322602969/3249622334");
        mInterstetialAdds.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                // beginPlayingGame();
            }
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (isAddLoaded){
                    mInterstetialAdds.show();
                }
                isAddLoaded=false;
            }
        });
        requestNewInterstitial();
    }
    private void checkPermission() {
        //ask for the permission in android M
        int permission = ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the SD-CARD is required for this app to Download PDF.")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        Log.i(TAG, "Clicked");
                        makeRequest();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                makeRequest();
            }
        }else{
            initMediaController();
        }
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE},
                CommonConstant.REQUEST_WRITE_STORAGE);
    }
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstetialAdds.loadAd(adRequest);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CommonConstant.REQUEST_WRITE_STORAGE: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user");

                } else {
                    initMediaController();
                    Log.i(TAG, "Permission has been granted by user");

                }
                return;
            }
        }
    }
}
