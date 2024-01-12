package edu.hw6.task6;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class PortMapper {

    private PortMapper() {
    }

    static Map<Integer, NetworkService> getNetworkServiceMap(File portsJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<Integer, NetworkService> portMap = new HashMap<>();

        JsonNode jsonNode = null;
        try {
            jsonNode = objectMapper.readTree(portsJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (jsonNode.isArray()) {
            Iterator<JsonNode> elements = jsonNode.elements();

            while (elements.hasNext()) {
                JsonNode element = elements.next();

                int port = element.get("port").asInt();
                String protocol = element.get("protocol").asText();
                String service = element.get("service").asText();

                NetworkService networkService = new NetworkService(protocol, port, service);
                portMap.put(port, networkService);

            }
        }

        return portMap;
    }

    record NetworkService(String protocol, int port, String service) {
    }
}
