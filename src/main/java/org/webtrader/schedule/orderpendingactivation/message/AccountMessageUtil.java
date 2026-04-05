package org.webtrader.schedule.orderpendingactivation.message;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountMessageUtil
{
    public static String accountIntoMessage( List<Integer> accountIDs )
    {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put( "accountIDs", accountIDs );
        jsonMap.put( "programName", "OrderPendingActivation.jar" );
        Gson gson = new Gson();

        return gson.toJson( jsonMap );
    }
}
