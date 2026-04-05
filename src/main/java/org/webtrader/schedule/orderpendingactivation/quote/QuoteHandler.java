package org.webtrader.schedule.orderpendingactivation.quote;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.webtrader.schedule.orderpendingactivation.PendingOrderProcessor;
import org.webtrader.schedule.orderpendingactivation.logs.Log;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.model.Quote;

import java.util.Timer;
import java.util.TimerTask;

public class QuoteHandler
{
    private static final long RECONNECT_INTERVAL_MS = 5_000;

    private final PendingOrderProcessor observer;

    public QuoteHandler( PendingOrderProcessor observer )
    {
        this.observer = observer;
    }

    public void start()
    {
        connect();
    }

    private void connect()
    {
        Log.write( "Connecting to quote server..." );
        WebSocketClient client = new QuoteWebSocket( this );
        client.connect();
    }

    public void scheduleReconnect()
    {
        new Timer().schedule( new TimerTask() {
            @Override
            public void run() {
                connect();
            }
        }, RECONNECT_INTERVAL_MS);
    }

    public void onOpen( ServerHandshake handshake )
    {
        Log.write( "QuoteWebSocket connected" );
    }

    public synchronized void onMessage( String message )
    {
        try
        {
            JSONObject json = new JSONObject( message );
            Quote quote = new Quote( json );

            observer.onQuoteReceived( quote );
        }
        catch ( Exception e )
        {
            throw new RuntimeException( "Invalid quote message", e );
        }
    }

    public void onClose( int code, String reason, boolean remote )
    {
        StringBuilder message = new StringBuilder( "QuoteWebSocket closed" );

        message.append( " [code=").append( code ) ;

        if ( reason != null && !reason.isBlank() )
        {
            message.append( ", reason=" ).append( reason );
        }

        message.append( ", remote=" ).append( remote ).append( "]" );

        LogConsole.print( message.toString() );

        scheduleReconnect();
    }

    public void onError( Exception e )
    {
        Log.write( "QuoteWebSocket error: " + e.getMessage() );
    }
}
