package org.webtrader.schedule.orderpendingactivation.http;

import org.webtrader.schedule.orderpendingactivation.logs.Log;
import org.webtrader.schedule.orderpendingactivation.logs.LogConsole;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSender
{
    private final HttpClient client = HttpClient.newHttpClient();

    public void postJson( URI uri, String json )
    {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            LogConsole.print("Notification sent to " + uri + ", status: " + response.statusCode());
        }
        catch ( Throwable e ) {
            Log.write("HttpSender: Failed to send POST request.\n"
                    + "URI: " + uri + "\n"
                    + "Payload: " + json + "\n"
                    + "Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
