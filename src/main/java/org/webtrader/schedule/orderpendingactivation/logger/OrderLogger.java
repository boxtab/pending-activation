package org.webtrader.schedule.orderpendingactivation.logger;

import java.util.List;

public class OrderLogger
{
    private final List<OrderLoggerBase> loggers;

    public OrderLogger( String pathToStorage )
    {
        this.loggers = List.of(
                new OrderFileLogger( pathToStorage )
                , new OrderDatabaseLogger( pathToStorage )
        );
    }

    public void log( OrderAccountEntry orderAccountEntry )
    {
        for ( OrderLoggerBase logger : loggers ) {
            logger.writeLog( orderAccountEntry );
        }
    }
}
