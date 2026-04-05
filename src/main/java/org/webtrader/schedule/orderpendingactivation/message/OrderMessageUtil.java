package org.webtrader.schedule.orderpendingactivation.message;

import org.json.JSONObject;
import org.webtrader.schedule.orderpendingactivation.order.OrderPending;
import org.webtrader.schedule.orderpendingactivation.order.constants.OrderStates;

public class OrderMessageUtil
{
    public static String orderIntoMessage( OrderPending order )
    {
        JSONObject body = new JSONObject();
        body.put( "channelName", order.getChannelName() );
        body.put( "eventName", "eventOrder" );
        body.put( "programName", "OrderPendingActivation.jar" );
        body.put( "eventType", "update" );
        body.put( "id", order.getId() );

        JSONObject data = new JSONObject();
        data.put( "state", OrderStates.POSITION );
        body.put( "data", data );

        return body.toString();
    }
}
