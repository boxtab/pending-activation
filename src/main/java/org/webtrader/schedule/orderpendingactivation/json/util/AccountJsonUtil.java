package org.webtrader.schedule.orderpendingactivation.json.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.webtrader.schedule.orderpendingactivation.account.Account;
import java.lang.reflect.Type;

public class AccountJsonUtil
{
    private static final Gson prettyGson = new GsonBuilder()
            .registerTypeHierarchyAdapter(Account.class, new AccountSerializer())
            .setPrettyPrinting()
            .create();

    public static String toPrettyJson(Account account)
    {
        return prettyGson.toJson(account);
    }

    private static class AccountSerializer implements JsonSerializer<Account>
    {
        @Override
        public JsonElement serialize(Account src, Type typeOfSrc, com.google.gson.JsonSerializationContext context)
        {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("id", src.getId());
            jsonObject.addProperty("user_id", src.getUserId());
            jsonObject.addProperty("currency_id", src.getCurrencyId());
            jsonObject.addProperty("balance", src.getBalance());
            jsonObject.addProperty("credit", src.getCredit());
            jsonObject.addProperty("leverage", src.getLeverage());
            jsonObject.addProperty("type", src.getType());
            jsonObject.addProperty("equity", src.getEquity());
            jsonObject.addProperty("margin", src.getMargin());
            jsonObject.addProperty("free_margin", src.getFreeMargin());
            jsonObject.addProperty("margin_indicator", src.getMarginIndicator());
            jsonObject.addProperty("margin_level", src.getMarginLevel());
            jsonObject.addProperty("profit_loss", src.getProfitLoss());
            jsonObject.addProperty("profit_loss_opened_orders", src.getProfitLossOpenedOrders());
            jsonObject.addProperty("status", src.getStatus());
            jsonObject.addProperty("login", src.getLogin());
            jsonObject.addProperty("uuid", src.getUuid());
            jsonObject.addProperty("last_login_time",
                    src.getLastLoginTime() != null ? src.getLastLoginTime().toString() : null
            );
            jsonObject.addProperty("created_at",
                    src.getCreatedAt() != null ? src.getCreatedAt().toString() : null
            );
            jsonObject.addProperty("updated_at",
                    src.getUpdatedAt() != null ? src.getUpdatedAt().toString() : null
            );
            jsonObject.addProperty("deleted_at",
                    src.getDeletedAt() != null ? src.getDeletedAt().toString() : null
            );

            return jsonObject;
        }
    }
}
