package com.epam.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class ConnectionPool {
    private static final int CONNECTION_COUNT = 5;
    private static ConnectionPool instance;
    private static Queue<Connection> connections = new ArrayDeque<>();

    static { // bad
        try {
            instance = new ConnectionPool();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ConnectionPool() throws IOException, SQLException {
        init();
    }

    public static ConnectionPool getInstance() throws IOException {
        if (instance == null) {
            throw new IOException("no instance");
        }
        return instance;
    }

    public Connection takeConnection() {
        return connections.poll();
    }

    public void returnConnection(Connection connection) {
        connections.add(connection);
    }

    private void init() throws IOException, SQLException {
        for (int i = 0; i < CONNECTION_COUNT; i++) {
            Connection connection = ConnectionCreator.getConnection();
            connections.add(connection);
        }
    }
}
