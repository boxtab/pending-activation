package org.webtrader.schedule.orderpendingactivation.order;

import org.webtrader.schedule.orderpendingactivation.logs.Log;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.databases.DBConnection;
import java.sql.PreparedStatement;

public class OrderDatabaseUpdater
{
    public void updateOrder( OrderPending order )
    {
        String sql = """
                UPDATE orders
                SET
                    expired = ?,
                    activation_price = ?,
                    state = ?,
                    open_price = ?,
                    updated_at = ?
                WHERE id = ?;
            """;

        try ( PreparedStatement stmt = DBConnection.getInstance().prepareStatement( sql ) ) {

            stmt.setObject(1, order.getExpired());
            stmt.setBigDecimal(2, order.getActivationPrice());
            stmt.setInt(3, order.getState());
            stmt.setBigDecimal(4, order.getOpenPrice());
            stmt.setTimestamp(5, order.getUpdatedAt());
            stmt.setInt(6, order.getId());

            stmt.executeUpdate();

            LogConsole.print( "OrderDatabaseUpdater: Order with ID " + order.getId() + " has been successfully updated." );

        } catch ( Exception e ) {
            Log.write( "OrderDatabaseUpdater: Failed to update order ID " + order.getId()
                    + ". Error: " + e.getClass().getSimpleName() + " - " + e.getMessage()
            );
        }
    }
}
