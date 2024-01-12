package edu.hw2.Task3;

public class DefaultConnectionManager implements ConnectionManager {

    @Override
    public Connection getConnection() {
        return (PseudoRandom.getBool())
            ? new FaultyConnection()
            : new StableConnection();
    }
}
