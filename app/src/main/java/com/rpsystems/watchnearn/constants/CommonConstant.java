package com.rpsystems.watchnearn.constants;

import com.rpsystems.watchnearn.utilities.PayPalConfig;

/**
 * Created by neosoft on 29/12/16.
 */

public class CommonConstant {
    public static String[] COUNTRYLIST = { "india", "US","Japan","England" };//array of strings used to populate the spinner
    public static String[] CITYLIST = { "pune", "mumbai","nashik","nagpur" };//array of strings used to populate the spinner
    public static String BASE_URL="https://api.sandbox.paypal.com";
    public static final int TAG_COUNTRY = 1;
    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;
    public static String VideoActivity="VideoViewActivity";
    public static final int REQUEST_WRITE_STORAGE = 112;
    public static final String ADCOLONY_APP_ID="app34884be21ea6465a8f";
    public static final String ADCOLONY_ZONE_ID="vz43df28276a3b4077bb";
    public static final String ADCOLONY_ZONE_NAME="Ad Zone #1";
    public static final String CHARTBOOST_APP_ID="58dcd3f4f6cd45798ee1d9db";
    public static final String CHARTBOOST_SIGNATURE_ID="12de10c945ea7e60840f9c0d7070ff359093e913";



    //Paypal Configuration Object

}
