package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QuotesServer implements AutoCloseable {

    private static final String[] QUOTES = new String[] {
        "Не переходи на личности там, где их нет",
        "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма.",
        "Чем ниже интеллект, тем громче оскорбления",
        "Если бы твой IQ был хотя бы на уровне твоего эго, ты бы уже давно стал гением.",
        "Люди как ты доказывают теорию эволюции – они идут назад."
    };

    private static final String DEFAULT_ANSWER = "Иногда лучше просто промолчать";
    private final int port;
    private final int maxConnections;
    private ServerSocket serverSocket;

    public QuotesServer(int port, int maxConnections) {
        this.port = port;
        this.maxConnections = maxConnections;
        try {
            this.serverSocket = new ServerSocket();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        if (serverSocket.isBound()) {
            throw new IllegalStateException("The server is already running");
        }
        new Thread(() -> {
            try (ExecutorService executorService = Executors.newFixedThreadPool(maxConnections)) {
                serverSocket.bind(new InetSocketAddress(port));
                while (!serverSocket.isClosed()) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.submit(new RequestHandler(clientSocket));
                }
            } catch (SocketException ignored) {
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void shutdown() {
        if (serverSocket.isClosed()) {
            throw new IllegalStateException("Server is already off");
        }
        if (!serverSocket.isBound()) {
            throw new IllegalStateException("The server has not started yet");
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        shutdown();
    }

    private record RequestHandler(Socket socket) implements Runnable {

        @Override
        public void run() {
            try (
                Socket socket = this.socket;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String word = bufferedReader.readLine().toLowerCase();

                int index = -1;
                for (int i = 0; i < QUOTES.length; i++) {
                    if (QUOTES[i].toLowerCase().contains(word)) {
                        index = i;
                        break;
                    }
                }
                printWriter.println(index != -1 ? QUOTES[index] : DEFAULT_ANSWER);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
