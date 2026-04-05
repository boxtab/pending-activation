package org.webtrader.schedule.orderpendingactivation.logger;

import org.webtrader.schedule.orderpendingactivation.json.util.AccountJsonUtil;
import org.webtrader.schedule.orderpendingactivation.json.util.OrderJsonUtil;
import org.webtrader.schedule.orderpendingactivation.logs.Log;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;
import org.webtrader.schedule.orderpendingactivation.databases.DBConnection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDatabaseLogger extends OrderLoggerBase
{
    public OrderDatabaseLogger(String pathToStorage)
    {
        super(pathToStorage);
    }

    @Override
    public void writeLog(OrderAccountEntry orderAccountEntry)
    {
        String sql = """
                INSERT INTO webtrader.order_records(
                    order_id,
                    date_time_created,
                    action,
                    who,
                    who_identify,
                    class_function,
                    file_path,
                    `order`,
                    account,
                    created_at,
                    updated_at)
                VALUES (?, NOW(), ?, ?, ?, ?, ?, ?, ?, NOW(), NOW());
                """;

        try (PreparedStatement stmt = DBConnection.getInstance().prepareStatement(sql)) {

            stmt.setInt(1, orderAccountEntry.order().getId());
            stmt.setString(2, getAction());
            stmt.setString(3, getWho());
            stmt.setString(4, getWhoIdentify());
            stmt.setString(5, getClassFunction());
            stmt.setString(6, getFilePath(orderAccountEntry));
            stmt.setString(7, OrderJsonUtil.toPrettyJson( orderAccountEntry.order() ) );
            stmt.setString(8, AccountJsonUtil.toPrettyJson( orderAccountEntry.account() ) );

            stmt.executeUpdate();
            LogConsole.print( "OrderDatabaseLogger: Log added to DB for order ID: "
                    + orderAccountEntry.order().getId() );

        } catch ( SQLException e ) {
            Log.write( "OrderDatabaseLogger: SQL error while inserting log for order ID "
                    + orderAccountEntry.order().getId() + ": " + e.getMessage() );
        } catch (Exception e) {
            Log.write( "OrderDatabaseLogger: Unexpected error while preparing log for order ID "
                    + orderAccountEntry.order().getId() + ": " + e.getMessage() );
        }
    }
}
