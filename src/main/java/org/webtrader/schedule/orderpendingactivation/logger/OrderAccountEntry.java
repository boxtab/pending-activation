package org.webtrader.schedule.orderpendingactivation.logger;

import org.webtrader.schedule.orderpendingactivation.order.OrderPending;
import org.webtrader.schedule.orderpendingactivation.account.Account;

public record OrderAccountEntry(OrderPending order, Account account)
{}
