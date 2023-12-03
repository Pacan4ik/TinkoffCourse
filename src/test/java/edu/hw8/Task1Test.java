package edu.hw8;

import edu.hw8.task1.QuotesClient;
import edu.hw8.task1.QuotesServer;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    void shouldReceiveResponse() throws InterruptedException {
        //given
        QuotesServer quotesServer = new QuotesServer(12345, 3);
        quotesServer.start();

        Thread.sleep(1000); //надеемся что сервер успеет подняться

        QuotesClient quotesClient = new QuotesClient("localhost", 12345);

        //when
        String response = quotesClient.askQuote("глупый");

        //then
        Assertions.assertEquals(
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
            response
        );

        quotesServer.close();
    }

    @Test
    void shouldHandleMultipleClients() throws InterruptedException {
        //given
        QuotesServer quotesServer = new QuotesServer(12345, 3);
        quotesServer.start();
        Thread.sleep(1000); //надеемся что сервер успеет подняться

        Callable<String> callable = () -> new QuotesClient("localhost", 12345).askQuote("интеллект");
        var tasks = Stream.generate(() -> callable).limit(20).toList();

        //when
        List<Future<String>> responses;
        try (ExecutorService executorService = Executors.newFixedThreadPool(20)) {
            responses = executorService.invokeAll(tasks);
        }

        //then
        org.assertj.core.api.Assertions.assertThat(responses.stream().map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                }
            ).toList())
            .allMatch(s -> {
                return s.equals("Чем ниже интеллект, тем громче оскорбления");
            });
        quotesServer.close();
    }

}
