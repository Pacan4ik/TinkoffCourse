package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class QuotesClient {

    private final String host;
    private final int port;

    public QuotesClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String askQuote(String word) {
        try (Socket socket = new Socket(host, port);
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            printWriter.println(word);
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
