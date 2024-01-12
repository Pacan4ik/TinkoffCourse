package edu.hw6.task6;

import java.io.IOException;

public class Main {

    private Main() {
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public static void main(String[] args) throws IOException {
        System.out.println(PortChecker.scanPorts());
    }
}
