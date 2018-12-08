package com.epam.connection;

import java.sql.Connection;
import java.util.*;

public class ConnectionPool {
    private static final int CONNECTION_COUNT = 5;
    private static ConnectionPool instance = new ConnectionPool();
    private static Queue<Connection> connections = new ArrayDeque<>();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connections.poll();
    }

    public void returnConnection(Connection connection) {
        connections.add(connection);
    }

    private void init() {
        for (int i = 0; i < CONNECTION_COUNT; i++) {
            Connection connection = ConnectionCreator.getConnection();
            connections.add(connection);
        }
    }
}
