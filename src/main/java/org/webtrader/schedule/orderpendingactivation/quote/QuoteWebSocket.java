package org.webtrader.schedule.orderpendingactivation.quote;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.webtrader.schedule.orderpendingactivation.configs.Config;

import java.net.URI;
import java.net.URISyntaxException;

public class QuoteWebSocket extends WebSocketClient
{
    private static final String HOST = Config.getConfig().getProperty( "QUOTATION_INFORMER_HOST" );

    private static final String PORT = Config.getConfig().getProperty( "QUOTATION_INFORMER_PORT" );

    private final QuoteHandler handler;

    public QuoteWebSocket( QuoteHandler handler )
    {
        super( createUri() );
        this.handler = handler;
    }

    private static URI createUri()
    {
        try
        {
            return new URI( "ws://" + HOST + ":" + PORT + "/" );
        }
        catch ( URISyntaxException e )
        {
            throw new RuntimeException( "Invalid WebSocket URI", e );
        }
    }

    @Override
    public void onOpen( ServerHandshake handshake )
    {
        handler.onOpen( handshake );
    }

    @Override
    public void onMessage( String message )
    {
        handler.onMessage( message );
    }

    @Override
    public void onClose( int code, String reason, boolean remote )
    {
        handler.onClose( code, reason, remote );
    }

    @Override
    public void onError( Exception e )
    {
        handler.onError(e);
    }
}
