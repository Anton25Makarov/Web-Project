package com.epam.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int CONNECTION_COUNT = 5;
    private static ConnectionPool instance;
    private static Queue<Connection> connections = new ArrayDeque<>();

    static {
        instance = new ConnectionPool();
    }

    private final Semaphore semaphore = new Semaphore(CONNECTION_COUNT, true);
    private Lock takeLock = new ReentrantLock();
    private Lock returnLock = new ReentrantLock();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection takeConnection() throws InterruptedException {
        semaphore.acquire();
        takeLock.lock();
        Connection connection;
        try {
            connection = connections.poll();
        } finally {
            takeLock.unlock();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        returnLock.lock();
        try {
            connections.add(connection);
        } finally {
            returnLock.unlock();
        }
        semaphore.release();
    }

    private void init() {
        try {
            for (int i = 0; i < CONNECTION_COUNT; i++) {
                Connection connection = ConnectionCreator.getConnection();
                connections.add(connection);
            }
        } catch (IOException | SQLException e) {
            throw new RuntimeException("Con not create connection to database", e);
        }
    }
}
