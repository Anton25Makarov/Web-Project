package com.epam.service;

import com.epam.connection.ConnectionPool;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;

public class Service implements Closeable { //????
    protected Connection connection;

    public Connection takeConnection() throws InterruptedException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        return connectionPool.takeConnection();
    }

    @Override
    public void close() throws IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.returnConnection(connection);
    }
}
