package org.webtrader.schedule.orderpendingactivation.utils;

import org.json.JSONObject;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;

public class QuoteDebugUtils
{
    public static void printQuote( JSONObject quote )
    {
        LogConsole.print
                (
                    quote.getString("symbol") + " " +
                    quote.getDouble("bid_price") + " " +
                    quote.getDouble("ask_price")
                );
    }
}
