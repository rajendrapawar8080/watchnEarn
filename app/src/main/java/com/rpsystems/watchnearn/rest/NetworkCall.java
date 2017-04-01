package com.rpsystems.watchnearn.rest;

import android.app.Activity;
import android.util.Log;


import com.rpsystems.watchnearn.constants.CommonConstant;
import com.rpsystems.watchnearn.controllers.interfaces.NetworkReceiver;
import com.rpsystems.watchnearn.rest.model.Countries;
import com.rpsystems.watchnearn.rest.model.requestpojos.PaymentRequest;
import com.rpsystems.watchnearn.rest.service.RestClient;
import com.rpsystems.watchnearn.rest.service.ServiceFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by neosoft on 30/12/16.
 */

public class NetworkCall {
    private static String TAG="NetworkCall";
    private Activity mActivity;
    private NetworkReceiver mReceiver;

    public NetworkCall(Activity mActivity, NetworkReceiver mReceiver) {
        this.mReceiver = mReceiver;
        this.mActivity = mActivity;
    }
    public void fetchWSCall(){
        RestClient service = ServiceFactory.createRetrofitService(RestClient.class, RestClient.SERVICE_BASEURL);
        service.getPaymentReposne()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PaymentRequest>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                        Log.d(TAG,"onCompleted");
                    }
                    @Override
                    public final void onError(Throwable e) {
                        Log.d(TAG,"onError"+e.getMessage());
                        mReceiver.onError(e.getMessage());
                    }
                    @Override
                    public final void onNext(PaymentRequest response) {
                        Log.d(TAG,"onNext");
                        postResult(response, CommonConstant.TAG_COUNTRY);

                    }
                });
    }
    public void postResult(PaymentRequest paymentRequest,int tag){

        switch (tag){
            case CommonConstant.TAG_COUNTRY:
                 mReceiver.onResponse(paymentRequest,tag);
                break;
            default:
                Log.d("default","default");
                break;
        }
    }

}
