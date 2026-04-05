package org.webtrader.schedule.orderpendingactivation;

import org.webtrader.schedule.orderpendingactivation.configs.Config;
import org.webtrader.schedule.orderpendingactivation.configs.RecalculatorConfig;
import org.webtrader.schedule.orderpendingactivation.logs.Log;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.model.Quote;
import org.webtrader.schedule.orderpendingactivation.notification.NotificationService;
import org.webtrader.schedule.orderpendingactivation.order.*;
import org.webtrader.schedule.orderpendingactivation.logger.OrderAccountEntry;
import org.webtrader.schedule.orderpendingactivation.logger.OrderAccountEntryFactory;
import org.webtrader.schedule.orderpendingactivation.logger.OrderLogger;
import org.webtrader.schedule.orderpendingactivation.utils.OrderDebugUtils;
import org.webtrader.schedule.orderpendingactivation.utils.OrderPendingDebugUtils;

import java.util.*;

public class PendingOrderProcessor
{
    private final NotificationService notificationService;
    private final OrderPendingActivator activator;
    private final OrderDatabaseUpdater updater;
    private final OrderFetcher orderFetcher;
    private final OrderAccountEntryFactory orderAccountEntryFactory;
    private final OrderLogger orderLogger;

    public PendingOrderProcessor()
    {
        Config config = Config.getConfig();

        String pathToStorage     = config.getProperty( "PATH_TO_STORAGE" );
        String recalculatorHost  = config.getProperty( "ACCOUNT_RECALCULATOR_HOST" );
        String recalculatorPort  = config.getProperty( "ACCOUNT_RECALCULATOR_PORT" );
        String recalculatorToken = config.getProperty( "ACCOUNT_RECALCULATOR_TOKEN" );

        this.orderFetcher = new OrderFetcher();
        this.activator = new OrderPendingActivator();
        this.updater = new OrderDatabaseUpdater();

        RecalculatorConfig recalculatorConfig = new RecalculatorConfig(
                recalculatorHost, recalculatorPort, recalculatorToken
        );
        this.notificationService = new NotificationService( recalculatorConfig );

        this.orderAccountEntryFactory = new OrderAccountEntryFactory();
        this.orderLogger = new OrderLogger(pathToStorage);
    }

    public void onQuoteReceived( Quote quote )
    {
        try
        {
            List<OrderPending> pendingOrders = orderFetcher.getPendingOrders( quote.getSymbol() );

            if ( pendingOrders.isEmpty() ) {
                return;
            }

            LogConsole.print( OrderPendingDebugUtils.formatOrdersList( pendingOrders ) );

            Set<Integer> accountIDs = new HashSet<>();

            for ( OrderPending pendingOrder : pendingOrders )
            {
                activator.activatePendingOrder( pendingOrder );
                updater.updateOrder( pendingOrder );
                notificationService.sendOrderNotification( pendingOrder );
                accountIDs.add( pendingOrder.getAccountId() );
                OrderDebugUtils.printOrder( pendingOrder );
            }

            notificationService.sendAccountNotification( new ArrayList<>( accountIDs ) );

            List<OrderAccountEntry> orderAccountEntries = orderAccountEntryFactory.createLogEntries( pendingOrders );
            orderAccountEntries.forEach( orderLogger::log );
        }
        catch ( Throwable e )
        {
            Log.write( "OrderPendingActivation.jar error: " + e );
        }
    }
}
