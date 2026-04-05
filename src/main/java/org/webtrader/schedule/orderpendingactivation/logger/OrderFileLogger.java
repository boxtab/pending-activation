package org.webtrader.schedule.orderpendingactivation.logger;

import org.webtrader.schedule.orderpendingactivation.json.util.AccountJsonUtil;
import org.webtrader.schedule.orderpendingactivation.json.util.OrderJsonUtil;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrderFileLogger extends OrderLoggerBase
{
    public OrderFileLogger( String pathToStorage )
    {
        super( pathToStorage );
    }

    @Override
    public void writeLog( OrderAccountEntry orderAccountEntry )
    {
        String filePath = getFilePath( orderAccountEntry );
        String content = getContent( orderAccountEntry );

        writeStringToFile( filePath, content );
    }

    private String getContent( OrderAccountEntry orderAccountEntry )
    {
        return  "\n[ " + getCurrentDateTime() + " ]\n" +
                getAction() + "\n" +
                "scheduler_name = " + getWhoIdentify() + "\n" +
                getClassFunction() + "\n" +
                "Order=\n" + OrderJsonUtil.toPrettyJson( orderAccountEntry.order() ) + "\n" +
                "Account=\n" + AccountJsonUtil.toPrettyJson( orderAccountEntry.account() );
    }

    private void writeStringToFile( String filePath, String content )
    {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        if ( parentDir != null && !parentDir.exists() ) {
            if ( ! parentDir.mkdirs() ) {
                LogConsole.print( "Failed to create directories: " + parentDir.getAbsolutePath() );
                LogConsole.print( "Check application permissions." );
                return;
            }
        }

        try ( BufferedWriter writer = new BufferedWriter( new FileWriter(file, true) ) )
        {
            writer.write(content);
            writer.newLine();
        }
        catch ( IOException e )
        {
            LogConsole.print( "Error writing to file: " + filePath + " | " + e.getMessage() );
        }
    }
}
