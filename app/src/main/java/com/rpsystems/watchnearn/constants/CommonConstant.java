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
    //Paypal Configuration Object

}
