package org.webtrader.schedule.orderpendingactivation.app;

import org.webtrader.schedule.orderpendingactivation.PendingOrderProcessor;
import org.webtrader.schedule.orderpendingactivation.quote.QuoteHandler;

public class App
{
    private final QuoteHandler quoteHandler;

    public App()
    {
        PendingOrderProcessor pendingOrderProcessor = new PendingOrderProcessor();
        this.quoteHandler = new QuoteHandler( pendingOrderProcessor );
    }

    public void start()  throws Exception
    {
        quoteHandler.start();
    }
}
