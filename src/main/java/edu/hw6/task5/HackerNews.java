package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private final HttpClient client;

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    public long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            List<Long> idsList = Arrays.stream(jsonString.substring(1, jsonString.length() - 1).split(","))
                .map(Long::parseLong).toList();
            long[] ids = new long[idsList.size()];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = idsList.get(i);
            }
            return ids;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
            .GET()
            .build();

        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonString = response.body();
            Pattern pattern = Pattern.compile("^.*\"title\":\"([^\"]*)\".*$");
            Matcher matcher = pattern.matcher(jsonString);
            if (matcher.matches()) {
                return matcher.group(1);
            }

        } catch (IOException | InterruptedException ignored) {
        }
        return null;
    }

}
