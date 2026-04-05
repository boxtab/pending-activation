package org.webtrader.schedule.orderpendingactivation.logs;

import org.webtrader.schedule.orderpendingactivation.configs.Config;

public class LogConsole
{
    private static final LogConsole INSTANCE = new LogConsole();

    private final String appMode;

    private LogConsole()
    {
        this.appMode = Config.getConfig().getProperty( "APP_MODE" );
    }

    public static void print( String message )
    {
        INSTANCE.printInternal( message );
    }

    private void printInternal( String message )
    {
        if ( "DEV".equalsIgnoreCase( appMode ) )
        {
            System.out.println( message );
        }
    }
}
