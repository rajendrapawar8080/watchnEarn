package com.rpsystems.watchnearn.rest.model.requestpojos;

public class Payer
{
    private String payment_method;

    public String getPayment_method ()
    {
        return payment_method;
    }

    public void setPayment_method (String payment_method)
    {
        this.payment_method = payment_method;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [payment_method = "+payment_method+"]";
    }
}
