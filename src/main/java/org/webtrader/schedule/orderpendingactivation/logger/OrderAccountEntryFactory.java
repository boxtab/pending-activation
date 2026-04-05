package org.webtrader.schedule.orderpendingactivation.logger;

import org.webtrader.schedule.orderpendingactivation.account.Account;
import org.webtrader.schedule.orderpendingactivation.account.AccountFetcher;
import org.webtrader.schedule.orderpendingactivation.order.OrderPending;
import java.util.List;
import java.util.Objects;

public class OrderAccountEntryFactory
{
    private static final AccountFetcher accountFetcher = new AccountFetcher();

    public List<OrderAccountEntry> createLogEntries(List<OrderPending> pendingOrders)
    {
        return pendingOrders.stream()
                .map(order -> {
                    Account account = accountFetcher.getAccountById( order.getAccountId() );
                    if (account != null) {
                        return new OrderAccountEntry(order, account);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList();
    }
}
