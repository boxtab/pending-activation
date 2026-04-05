package org.webtrader.schedule.orderpendingactivation.json.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.webtrader.schedule.orderpendingactivation.order.Order;
import java.lang.reflect.Type;

public class OrderJsonUtil
{
    private static final Gson prettyGson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Order.class, new OrderSerializer())
            .setPrettyPrinting()
            .create();

    public static String toPrettyJson(Order order)
    {
        return prettyGson.toJson(order);
    }

    private static class OrderSerializer implements JsonSerializer<Order>
    {
        @Override
        public JsonElement serialize(Order src, Type typeOfSrc, com.google.gson.JsonSerializationContext context)
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", src.getId());
            jsonObject.addProperty("type", src.getType());
            jsonObject.addProperty("quoteId", src.getQuoteId());
            jsonObject.addProperty("currencyId", src.getCurrencyId());
            jsonObject.addProperty("lots", src.getLots());
            jsonObject.addProperty("units", src.getUnits());
            jsonObject.addProperty("margin", src.getMargin());
            jsonObject.addProperty("stopLossKey", src.getStopLossKey());
            jsonObject.addProperty("stopLossValue", src.getStopLossValue());
            jsonObject.addProperty("takeProfitKey", src.getTakeProfitKey());
            jsonObject.addProperty("takeProfitValue", src.getTakeProfitValue());
            jsonObject.addProperty("expired",
                    src.getExpired() != null ? src.getExpired().toString() : null
            );
            jsonObject.addProperty("activationPrice", src.getActivationPrice());
            jsonObject.addProperty("openPrice", src.getOpenPrice());
            jsonObject.addProperty("openRate", src.getOpenRate());
            jsonObject.addProperty("closePrice", src.getClosePrice());
            jsonObject.addProperty("closeRate", src.getCloseRate());
            jsonObject.addProperty("commission", src.getCommission());
            jsonObject.addProperty("swaps", src.getSwaps());
            jsonObject.addProperty("profitLoss", src.getProfitLoss());
            jsonObject.addProperty("state", src.getState());
            jsonObject.addProperty("accountId", src.getAccountId());
            jsonObject.addProperty("closingDate",
                    src.getClosingDate() != null ? src.getClosingDate().toString() : null
            );
            jsonObject.addProperty("createdAt",
                    src.getCreatedAt() != null ? src.getCreatedAt().toString() : null
            );
            jsonObject.addProperty("updatedAt",
                    src.getUpdatedAt() != null ? src.getUpdatedAt().toString() : null
            );
            jsonObject.addProperty("deletedAt",
                    src.getDeletedAt() != null ? src.getDeletedAt().toString() : null
            );

            return jsonObject;
        }
    }
}
