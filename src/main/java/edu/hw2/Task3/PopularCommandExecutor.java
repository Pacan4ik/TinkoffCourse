package edu.hw2.Task3;

public final class PopularCommandExecutor {
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int limitAttempts) {
        this.manager = manager;
        maxAttempts = limitAttempts;
    }

    public void updatePackages() throws ConnectionException {
        tryExecute("apt update && apt upgrade -y");
    }

    private void tryExecute(String command) throws ConnectionException {
        for (int i = 1; i <= maxAttempts; i++) {
            try (var connection = manager.getConnection()) {
                connection.execute(command);
                break;
            } catch (Exception e) {
                if (i == maxAttempts) {
                    throw new ConnectionException(String.format("Failed to execute after %d attempts", i), e);
                }
            }
        }
    }
}
