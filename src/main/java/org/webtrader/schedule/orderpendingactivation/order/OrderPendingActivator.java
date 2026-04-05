package org.webtrader.schedule.orderpendingactivation.order;

import org.webtrader.schedule.orderpendingactivation.order.constants.OrderStates;
import org.webtrader.schedule.orderpendingactivation.order.constants.OrderTypes;

public class OrderPendingActivator
{
    public void activatePendingOrder( OrderPending orderPending )
    {
        orderPending.setExpired( null );
        orderPending.setActivationPrice( null );
        orderPending.setState( OrderStates.POSITION );
        orderPending.setOpenPrice(
                                    orderPending.getType() == OrderTypes.BUY
                                    ? orderPending.getAskPrice()
                                    : orderPending.getBidPrice()
                                );
        orderPending.setUpdatedAt( orderPending.getCurrentDateTime() );
    }
}
