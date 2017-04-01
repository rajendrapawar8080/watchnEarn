package com.rpsystems.watchnearn.rest.model.requestpojos;

public class Transactions
{
    private Amount amount;

    public Amount getAmount ()
    {
        return amount;
    }

    public void setAmount (Amount amount)
    {
        this.amount = amount;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+"]";
    }
}

	