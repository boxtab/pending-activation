package org.webtrader.schedule.orderpendingactivation.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class OrderPending extends Order
{
    private final Timestamp currentDateTime;
    private final BigDecimal bidPrice;
    private final BigDecimal askPrice;
    private final String channelName;

    public OrderPending( ResultSet rs ) throws SQLException
    {
        super( rs );

        this.currentDateTime = Timestamp.from( ZonedDateTime.now( ZoneOffset.UTC ).toInstant() );
        this.bidPrice = rs.getBigDecimal( "bid_price" );
        this.askPrice = rs.getBigDecimal( "ask_price" );
        this.channelName = rs.getString(  "channel_name" );
    }

    public BigDecimal getBidPrice()
    {
        return bidPrice;
    }

    public BigDecimal getAskPrice()
    {
        return askPrice;
    }

    public String getChannelName()
    {
        return channelName;
    }

    public Timestamp getCurrentDateTime()
    {
        return currentDateTime;
    }
}
