package com.rpsystems.watchnearn.rest.service;
import com.rpsystems.watchnearn.rest.model.Countries;
import com.rpsystems.watchnearn.rest.model.requestpojos.PaymentRequest;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by neosoft on 29/12/16.
 */

public interface RestClient {
    String SERVICE_BASEURL = "https://api.sandbox.paypal.com";

    @GET("country/get/all")
    Observable<Countries> getUser();

    @Headers({
            "Content-Type: application/json",
            "Authorization: Bearer A101.pcvzfGJ_GlbtUvkPU89I6h6lBdvWHElMW8zSD-Mp17IPaf0ryHU3Ahq5zC7l_dsY.fRJ_Mg2-"
    })
    @GET("/v1/payments/payment")
    Observable<PaymentRequest> getPaymentReposne();

}
