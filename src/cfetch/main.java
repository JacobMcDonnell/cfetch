package cfetch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.*;

public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /* System date is required to make the API call use local date not server date */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        JSONObject obj;

        if (args.length > 0) {
            obj = request("http://calapi.inadiutorium.cz/api/v0/en/calendars/general-en/"
                    + args[0]);
        } else {
            obj = request("http://calapi.inadiutorium.cz/api/v0/en/calendars/general-en/"
                    + dtf.format(now));
        }

        JSONArray arr = obj.getJSONArray("celebrations");

        String date, season, title, color, celebration, output;

        date = obj.getString("date");
        season = obj.getString("season");
        title = arr.getJSONObject(0).getString("title");
        color = arr.getJSONObject(0).getString("colour");

        output = date + "\nToday: " + title + "\nSeason: " + season + "\nColor: " + color;

        if (arr.length() > 1) {
            celebration = arr.getJSONObject(1).getString("title");
            output += "\nCelebration: " + celebration;
        }

        System.out.println(output);
    }

    private static JSONObject request(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        return new JSONObject(jsonString);
    }
}