package org.webtrader.schedule.orderpendingactivation.order;

import org.webtrader.schedule.orderpendingactivation.databases.DBConnection;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.order.constants.OrderStates;
import org.webtrader.schedule.orderpendingactivation.order.constants.OrderTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderFetcher
{
    public List<OrderPending> getPendingOrders( String symbol )
    {
        try
        {
            return this.fetchFromDB( symbol );
        }
        catch ( Exception e )
        {
            LogConsole.print( "OrderFetcher: DB error while fetching pending orders for symbol '" + symbol + "': "
                    + e.getClass().getSimpleName() + " - " + e.getMessage()
            );
            return new ArrayList<>();
        }
    }

    private List<OrderPending> fetchFromDB( String symbol ) throws Exception
    {
        List<OrderPending> orders = new ArrayList<>();

        try ( PreparedStatement stmt = getPreparedStatement() )
        {
            stmt.setString(1, symbol);
            stmt.setInt(2, OrderStates.PENDING);
            stmt.setInt(3, OrderTypes.BUY);
            stmt.setInt(4, OrderTypes.SELL);

            try ( ResultSet rs = stmt.executeQuery() )
            {
                while ( rs.next() )
                {
                    orders.add( new OrderPending( rs ) );
                }
            }
        }

        return orders;
    }

    private static PreparedStatement getPreparedStatement() throws Exception
    {
        String sql = """
                SELECT 
                    orders.*, 
                    quote.bid_price, 
                    quote.ask_price, 
                    accounts.uuid as channel_name
                FROM orders
                INNER JOIN quote ON quote.id = orders.quote_id
                INNER JOIN accounts ON accounts.id = orders.account_id
                WHERE quote.symbol = ?
                  AND quote.is_deleted = 0
                  AND orders.deleted_at IS NULL
                  AND orders.state = ?
                  AND (
                        (orders.type = ? AND quote.ask_price <= orders.activation_price)
                        OR
                        (orders.type = ? AND quote.bid_price >= orders.activation_price)
                  );
            """;

        Connection connection = DBConnection.getInstance();
        return connection.prepareStatement( sql );
    }
}
