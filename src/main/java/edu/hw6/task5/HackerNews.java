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

public class HackerNews implements AutoCloseable {
    private final HttpClient client;

    public HackerNews(HttpClient client) {
        this.client = client;
    }

    public long[] hackerNewsTopStories() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
        String jsonString = response.body();
        List<Long> idsList = Arrays.stream(jsonString.substring(1, jsonString.length() - 1).split(","))
            .map(Long::parseLong).toList();
        long[] ids = new long[idsList.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = idsList.get(i);
        }
        return ids;
    }

    public String news(long id) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
            .GET()
            .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException ignored) {
            return null;
        }
        String jsonString = response.body();
        Pattern pattern = Pattern.compile("^.*\"title\":\"([^\"]*)\".*$");
        Matcher matcher = pattern.matcher(jsonString);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override
    public void close() {
        client.close();
    }
}
