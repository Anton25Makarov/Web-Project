package com.epam.library.service;

import com.epam.library.connection.ConnectionPool;

import java.io.Closeable;
import java.sql.Connection;

public abstract class Service implements Closeable {
    protected Connection connection;
    private ConnectionPool connectionPool;

    public Service() throws InterruptedException {
        connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
    }

    @Override
    public void close() {
        connectionPool.returnConnection(connection);
    }
}
