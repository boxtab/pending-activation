package org.webtrader.schedule.orderpendingactivation.logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

// Абстрактный класс, который задает общий процесс логирования
public abstract class OrderLoggerBase
{
    protected final String pathToStorage;

    public OrderLoggerBase(String pathToStorage)
    {
        this.pathToStorage = pathToStorage;
    }

    public abstract void writeLog( OrderAccountEntry orderAccountEntry );

    protected String getFilePath( OrderAccountEntry orderAccountEntry )
    {
        return this.pathToStorage +
                orderAccountEntry.account().getId() +
                File.separator +
                "order" +
                File.separator +
                orderAccountEntry.order().getId() +
                ".log";
    }

    protected String getCurrentDateTime()
    {
        String DATETIME_FORMAT          = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormatter  = new SimpleDateFormat( DATETIME_FORMAT );

        return dateFormatter.format( new Date() );
    }

    protected String getAction()
    {
        return "PENDING_TO_POSITION";
    }

    protected String getWho()
    {
        return "scheduler_name";
    }

    protected String getWhoIdentify()
    {
        return "OrderPendingActivation.jar";
    }

    protected String getClassFunction()
    {
        return "OrderPendingActivator::activatePendingOrder";
    }
}
