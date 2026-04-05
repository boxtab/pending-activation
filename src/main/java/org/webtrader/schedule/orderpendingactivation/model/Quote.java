package org.webtrader.schedule.orderpendingactivation.model;

import org.json.JSONObject;

public class Quote
{
    private final String symbol;
    private final double bidPrice;
    private final double askPrice;

    public Quote( JSONObject json )
    {
        this.symbol =   json.getString( "symbol" );
        this.bidPrice = json.getDouble( "bid_price" );
        this.askPrice = json.getDouble( "ask_price" );
    }

    public String getSymbol()
    {
        return symbol;
    }

    public double getBidPrice()
    {
        return bidPrice;
    }

    public double getAskPrice()
    {
        return askPrice;
    }
}
