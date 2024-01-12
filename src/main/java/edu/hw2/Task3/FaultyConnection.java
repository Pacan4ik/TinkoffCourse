package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) throws Exception {
        if (PseudoRandom.getBool()) {
            throw new Exception("Unable to connect");

        }
        LOGGER.info("executed: " + command);
    }

    @Override
    public void close() {
        LOGGER.info("connection closed");
    }
}
