package com.rpsystems.watchnearn.rest.model.requestpojos;

public class Redirect_urls
{
    private String cancel_url;

    private String return_url;

    public String getCancel_url ()
    {
        return cancel_url;
    }

    public void setCancel_url (String cancel_url)
    {
        this.cancel_url = cancel_url;
    }

    public String getReturn_url ()
    {
        return return_url;
    }

    public void setReturn_url (String return_url)
    {
        this.return_url = return_url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cancel_url = "+cancel_url+", return_url = "+return_url+"]";
    }
}
