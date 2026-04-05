package org.webtrader.schedule.orderpendingactivation.order;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Order
{
    protected int id;
    protected int type;
    protected Integer quoteId;
    protected Integer currencyId;
    protected BigDecimal lots;
    protected BigDecimal units;
    protected BigDecimal margin;
    protected Integer stopLossKey;
    protected BigDecimal stopLossValue;
    protected Integer takeProfitKey;
    protected BigDecimal takeProfitValue;
    protected Timestamp expired;
    protected BigDecimal activationPrice;
    protected BigDecimal openPrice;
    protected BigDecimal openRate;
    protected BigDecimal closePrice;
    protected BigDecimal closeRate;
    protected BigDecimal commission;
    protected BigDecimal swaps;
    protected BigDecimal profitLoss;
    protected Integer state;
    protected Integer accountId;
    protected Timestamp closingDate;
    protected Timestamp createdAt;
    protected Timestamp updatedAt;
    protected Timestamp deletedAt;

    public int getId() { return id; }
    public int getType() { return type; }
    public Integer getQuoteId() { return quoteId; }
    public Integer getCurrencyId() { return currencyId; }
    public BigDecimal getLots() { return lots; }
    public BigDecimal getUnits() { return units; }
    public BigDecimal getMargin() { return margin; }
    public Integer getStopLossKey() { return stopLossKey; }
    public BigDecimal getStopLossValue() { return stopLossValue; }
    public Integer getTakeProfitKey() { return takeProfitKey; }
    public BigDecimal getTakeProfitValue() { return takeProfitValue; }
    public Timestamp getExpired() { return expired; }
    public BigDecimal getActivationPrice() { return activationPrice; }
    public BigDecimal getOpenPrice() { return openPrice; }
    public BigDecimal getOpenRate() { return openRate; }
    public BigDecimal getClosePrice() { return closePrice; }
    public BigDecimal getCloseRate() { return closeRate; }
    public BigDecimal getCommission() { return commission; }
    public BigDecimal getSwaps() { return swaps; }
    public BigDecimal getProfitLoss() { return profitLoss; }
    public int getState() { return state; }
    public int getAccountId() { return accountId; }
    public Timestamp getClosingDate() { return closingDate; }
    public Timestamp getCreatedAt() { return createdAt; }
    public Timestamp getUpdatedAt() { return updatedAt; }
    public Timestamp getDeletedAt() { return deletedAt; }
    public void setExpired(Timestamp expired) { this.expired = expired; }
    public void setActivationPrice( BigDecimal activationPrice ) { this.activationPrice = activationPrice; }
    public void setState( Integer state ) { this.state = state; }
    public void setOpenPrice( BigDecimal openPrice ) { this.openPrice = openPrice; }
    public void setUpdatedAt( Timestamp updatedAt ) { this.updatedAt = updatedAt; }

    public Order( ResultSet rs ) throws SQLException
    {
        this.id = rs.getInt("id");
        this.type = rs.getInt("type");
        this.quoteId = rs.getObject("quote_id") != null ? rs.getInt("quote_id") : null;
        this.currencyId = rs.getObject("currency_id") != null ? rs.getInt("currency_id") : null;
        this.lots = rs.getObject("lots") != null ? rs.getBigDecimal("lots") : null;
        this.units = rs.getObject("units") != null ? rs.getBigDecimal("units") : null;
        this.margin = rs.getObject("margin") != null ? rs.getBigDecimal("margin") : null;
        this.stopLossKey = rs.getObject("stop_loss_key") != null ? rs.getInt("stop_loss_key") : null;
        this.stopLossValue = rs.getObject("stop_loss_value") != null ? rs.getBigDecimal("stop_loss_value") : null;
        this.takeProfitKey = rs.getObject("take_profit_key") != null ? rs.getInt("take_profit_key") : null;
        this.takeProfitValue = rs.getObject("take_profit_value") != null ? rs.getBigDecimal("take_profit_value") : null;
        this.expired = rs.getTimestamp("expired");
        this.activationPrice = rs.getObject("activation_price") != null ? rs.getBigDecimal("activation_price") : null;
        this.openPrice = rs.getObject("open_price") != null ? rs.getBigDecimal("open_price") : null;
        this.openRate = rs.getObject("open_rate") != null ? rs.getBigDecimal("open_rate") : null;
        this.closePrice = rs.getObject("close_price") != null ? rs.getBigDecimal("close_price") : null;
        this.closeRate = rs.getObject("close_rate") != null ? rs.getBigDecimal("close_rate") : null;
        this.commission = rs.getObject("commission") != null ? rs.getBigDecimal("commission") : null;
        this.swaps = rs.getObject("swaps") != null ? rs.getBigDecimal("swaps") : null;
        this.profitLoss = rs.getObject("profit_loss") != null ? rs.getBigDecimal("profit_loss") : null;
        this.state = rs.getObject("state") != null ? rs.getInt("state") : null;
        this.accountId = rs.getObject("account_id") != null ? rs.getInt("account_id") : null;
        this.closingDate = rs.getTimestamp("closing_date");
        this.createdAt = rs.getTimestamp("created_at");
        this.updatedAt = rs.getTimestamp("updated_at");
        this.deletedAt = rs.getTimestamp("deleted_at");
    }
}
