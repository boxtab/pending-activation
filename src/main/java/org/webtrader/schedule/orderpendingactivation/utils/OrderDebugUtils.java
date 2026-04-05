package org.webtrader.schedule.orderpendingactivation.utils;

import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.order.OrderPending;

public class OrderDebugUtils
{
    public static void printOrder( OrderPending order )
    {
        String orderDetails = String.format(
                "Order ID: %d, Quote ID: %d, Activation Price: %s, " +
                        "State: %s, Account ID: %d, Created At: %s",
                order.getId(),
                order.getQuoteId(),
                order.getActivationPrice(),
                order.getState(),
                order.getAccountId(),
                order.getCreatedAt()
        );
        LogConsole.print( orderDetails );
    }
}
