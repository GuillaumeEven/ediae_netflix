package com.ediae.netflix.utils;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class ConnexionTmdb {
    private static final Dotenv DOTENV = Dotenv.configure().ignoreIfMissing().load();
    private static final String API_KEY = DOTENV.get("APIKEY", "your_default_api_key");
    private static final String API_URL = DOTENV.get("APIURL", "https://api.themoviedb.org/3");


    public static void searchMovie(String query) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        String url = API_URL + "/search/movie?api_key=" + API_KEY + "&query=" + encodedQuery;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();
            System.out.println(responseBody);

            JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

            JsonArray results = jsonResponse.getAsJsonArray("results");

            System.out.println("Search results for: " + query);

            // for (JsonElement result : results) {
            //     JsonObject movie = result.getAsJsonObject();
            //     String title = movie.get("title").getAsString();
            //     String releaseDate = movie.get("release_date").getAsString();
            //     System.out.println("Title: " + title + ", Release Date: " + releaseDate
            // }

            for (int i = 0; i < results.size(); i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                String title = movie.get("title").getAsString();
                String releaseDate = movie.get("release_date").getAsString();
                System.out.println("Title: " + title + ", Release Date: " + releaseDate);
            }
        }
    }
}
