package edu.hw6.task6;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class PortChecker {

    private static final Map<Integer, PortMapper.NetworkService> SERVICE_MAP;

    static {
        try {
            SERVICE_MAP = PortMapper.getNetworkServiceMap(new File(
                Objects.requireNonNull(PortMapper.class.getResource("/knownports.json")).toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static final int START_PORT = 0;
    private static final int END_PORT = 49151;
    private static final int OFFSET = 4;

    private PortChecker() {

    }

    public static String scanPorts() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Протокол\tПорт\tСервис\n");
        for (int port = START_PORT; port <= END_PORT; port++) {
            Protocol protocol = tryPort(port);
            if (protocol.tcp || protocol.udp) {
                stringBuilder.append(composeInfoString(protocol, port)).append('\n');
            }
        }
        return stringBuilder.toString();
    }

    private static String composeInfoString(Protocol protocol, int port) {
        StringJoiner joiner = new StringJoiner("\t");
        joiner.add(protocol + (protocol.toString().length() < OFFSET ? "\t\t" : "\t"));
        joiner.add(port + (Integer.toString(port).length() < OFFSET ? "\t" : ""));
        PortMapper.NetworkService service = SERVICE_MAP.get(port);
        if (service != null) {
            joiner.add(service.service());
        }
        return joiner.toString();
    }

    public static Protocol tryPort(int port) {
        boolean tcp;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            tcp = false;
        } catch (IOException e) {
            tcp = true;
        }

        boolean udp;
        try (DatagramSocket datagramSocket = new DatagramSocket(port)) {
            udp = false;
        } catch (SocketException e) {
            udp = true;
        }
        return new Protocol(tcp, udp);
    }

    public record Protocol(boolean tcp, boolean udp) {
        @Override
        public String toString() {
            if (tcp && udp) {
                return "TCP/UDP";
            }
            if (tcp) {
                return "TCP";
            }
            if (udp) {
                return "UDP";
            }
            return "";
        }
    }
}
