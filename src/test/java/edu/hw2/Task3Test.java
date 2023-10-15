package edu.hw2;

import edu.hw2.Task3.Connection;
import edu.hw2.Task3.ConnectionException;
import edu.hw2.Task3.DefaultConnectionManager;
import edu.hw2.Task3.FaultyConnection;
import edu.hw2.Task3.FaultyConnectionManager;
import edu.hw2.Task3.PopularCommandExecutor;
import edu.hw2.Task3.StableConnection;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {

    @Test
    @DisplayName("Удаленный сервер (Task3). DefaultConnectionManager возвращает и проблемное соединение, и стабильное")
    void DefaultCMReturnsBothConnection() {
        //given
        var dcm = new DefaultConnectionManager();

        //when
        Connection[] connections = {dcm.getConnection(), dcm.getConnection(), dcm.getConnection(), dcm.getConnection()};

        //then
        assertTrue(Arrays.stream(connections)
            .anyMatch(connection ->
                (connection instanceof FaultyConnection))
            && Arrays.stream(connections)
            .anyMatch(connection ->
                (connection instanceof StableConnection))
        );
    }

    @Test
    @DisplayName("Удаленный сервер (Task3). FaultConnectionManager возвращает только проблемное соединение")
    void FaultyCMAlwaysReturnsFaultyConnection() {
        //given
        var fcm = new FaultyConnectionManager();

        //when
        Connection[] connections = {fcm.getConnection(), fcm.getConnection(), fcm.getConnection(), fcm.getConnection()};

        //then
        assertTrue(Arrays.stream(connections)
            .allMatch(connection ->
                connection instanceof FaultyConnection));
    }

    @Test
    @DisplayName("Удаленный сервер (Task3). UpdatePackages возвращает Connection Exception")
    void updatePackagesThrowsConnectionException() {
        //given
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 2);

        //then
        assertThrows(ConnectionException.class, executor::updatePackages);

    }

    @Test
    @DisplayName("Удаленный сервер (Task3). UpdatePackages выполняется успешно (FaultyCM).")
    void updatePackagesSucRunsSuccessfully() {
        //given
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 4);

        //then
        assertDoesNotThrow(executor::updatePackages);

    }

    @Test
    @DisplayName("Удаленный сервер (Task3). UpdatePackages выполняется успешно (DefaultCM).")
    void updatePackagesSucRunsSuccessfully2() {
        //given
        var executor = new PopularCommandExecutor(new FaultyConnectionManager(), 3);

        //then
        assertDoesNotThrow(executor::updatePackages);

    }
}
