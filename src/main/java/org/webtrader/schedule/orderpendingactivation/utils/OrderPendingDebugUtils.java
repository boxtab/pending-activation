package org.webtrader.schedule.orderpendingactivation.utils;

import org.webtrader.schedule.orderpendingactivation.order.OrderPending;
import org.webtrader.schedule.orderpendingactivation.order.constants.OrderTypes;
import java.util.List;

public class OrderPendingDebugUtils
{
    public static String formatOrdersList( List<OrderPending> pendingOrders )
    {
        if (pendingOrders == null || pendingOrders.isEmpty()) {
            return "No orders found";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Found ").append(pendingOrders.size()).append(" pending orders:\n");

        for (OrderPending order : pendingOrders) {
            sb.append(formatDetailedOrderInfo(order)).append("\n\n");
        }

        return sb.toString();
    }

    public static String formatDetailedOrderInfo( OrderPending order )
    {
        if (order == null) {
            return "Order is null";
        }

        return "Order Details:\n" +
                "  ID: " + order.getId() + "\n" +
                "  Type: " + (order.getType() == OrderTypes.BUY ? "BUY" : "SELL") + "\n" +
                "  Account: " + order.getAccountId() + "\n" +
                "  Activation Price: " + order.getActivationPrice() + "\n" +
                "  Bid/Ask: " + order.getBidPrice() + "/" + order.getAskPrice() + "\n" +
                "  Lots: " + order.getLots() + "\n" +
                "  Channel: " + order.getChannelName() + "\n" +
                "  Created: " + order.getCreatedAt();
    }
}
