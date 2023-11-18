package edu.hw6;

import edu.hw6.task5.HackerNews;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task5Test {

    private static final String MOCKED_RESPONSE_IDS_STRING;

    static {
        try {
            MOCKED_RESPONSE_IDS_STRING = Files.readString(Path.of(Objects.requireNonNull(Task5Test.class.getResource(
                "/hw6Task5/topStoriesResponseIDs.json")).toURI())).strip();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String MOCKED_RESPONSE_NEWS_STRING;

    static {
        try {
            MOCKED_RESPONSE_NEWS_STRING = Files.readString(Path.of(Objects.requireNonNull(Task5Test.class.getResource(
                "/hw6Task5/38281944.json")).toURI())).strip();

        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static HttpClient mockClient;
    private static HttpClient mockClientIOException;

    @BeforeAll
    static void prepareMock() throws IOException, InterruptedException {
        mockClient = Mockito.mock(HttpClient.class);
        HttpResponse<String> mockedResponseIDs = Mockito.mock(HttpResponse.class);
        when(mockedResponseIDs.body())
            .thenReturn(MOCKED_RESPONSE_IDS_STRING);

        HttpRequest expectedRequestIDs = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        when(mockClient.send(eq(expectedRequestIDs), eq(HttpResponse.BodyHandlers.ofString())))
            .thenReturn(mockedResponseIDs);

        HttpResponse<String> mockedResponseNews = Mockito.mock(HttpResponse.class);
        when(mockedResponseNews.body())
            .thenReturn(MOCKED_RESPONSE_NEWS_STRING);

        HttpRequest expectedRequestNews = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/item/38281944.json"))
            .GET()
            .build();
        when(mockClient.send(eq(expectedRequestNews), eq(HttpResponse.BodyHandlers.ofString())))
            .thenReturn(mockedResponseNews);
    }

    @BeforeAll
    static void prepareMockIOException() throws IOException, InterruptedException {
        mockClientIOException = Mockito.mock(HttpClient.class);
        when(mockClientIOException.send(any(), eq(HttpResponse.BodyHandlers.ofString())))
            .thenThrow(IOException.class);
    }

    @Test
    void shouldReturnId() {
        //given
        HackerNews hackerNews = new HackerNews(mockClient);

        //when
        long[] result = hackerNews.hackerNewsTopStories();

        //then
        Assertions.assertThat(Arrays.toString(result).replace(" ", ""))
            .isEqualTo(MOCKED_RESPONSE_IDS_STRING);

    }

    @Test
    void shouldReturnTitle() {
        //given
        HackerNews hackerNews = new HackerNews(mockClient);
        int newsID = 38281944;

        //when
        String title = hackerNews.news(38281944);

        //then
        assertEquals(
            "NTSB Calls for Technology to Reduce Speeding in All New Cars",
            title
        );

    }

    @Test
    void shouldReturnEmptyIDs() {
        //given
        HackerNews hackerNews = new HackerNews(mockClientIOException);

        //when
        long[] result = hackerNews.hackerNewsTopStories();

        //then
        Assertions.assertThat(result).isEmpty();

    }

    @Test
    void shouldReturnNullNews() {
        //given
        HackerNews hackerNews = new HackerNews(mockClientIOException);

        //when
        String news = hackerNews.news(1);

        //then
        Assertions.assertThat(news).isNull();

    }
}
