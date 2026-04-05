package org.webtrader.schedule.orderpendingactivation.notification;

import org.webtrader.schedule.orderpendingactivation.configs.RecalculatorConfig;
import org.webtrader.schedule.orderpendingactivation.message.AccountMessageUtil;
import org.webtrader.schedule.orderpendingactivation.message.OrderMessageUtil;
import org.webtrader.schedule.orderpendingactivation.order.OrderPending;
import org.webtrader.schedule.orderpendingactivation.http.HttpSender;

import java.net.URI;
import java.util.List;

public class NotificationService
{
    private final RecalculatorConfig config;
    private final HttpSender sender;

    public NotificationService( RecalculatorConfig config )
    {
        this.config = config;
        this.sender = new HttpSender();
    }

    public void sendOrderNotification( OrderPending order )
    {
        String message = OrderMessageUtil.orderIntoMessage(order);
        URI uri = buildUri("/api/v1/demon/refresh-orders-list/");
        sender.postJson(uri, message);
    }

    public void sendAccountNotification( List<Integer> accountIDs )
    {
        String message = AccountMessageUtil.accountIntoMessage( accountIDs );
        URI uri = buildUri("/api/v1/demon/refresh-balance-panel/");
        sender.postJson(uri, message);
    }

    private URI buildUri(String endpoint)
    {
        return URI.create("http://" + config.host() + ":" + config.port() + endpoint + config.token());
    }
}
