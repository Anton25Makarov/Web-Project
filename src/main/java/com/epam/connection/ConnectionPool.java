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

    static { // bad (how make it another way?)
        try {
            instance = new ConnectionPool();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private final Semaphore semaphore = new Semaphore(CONNECTION_COUNT, true);
    private Lock takeLock = new ReentrantLock();
    private Lock returnLock = new ReentrantLock();

    private ConnectionPool() throws IOException, SQLException {
        init();
    }

    public static ConnectionPool getInstance() throws IOException {
        if (instance == null) {
            throw new IOException("no instance");
        }
        return instance;
    }

    public Connection takeConnection() throws InterruptedException { // разве не нужны локи?
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

    private void init() throws IOException, SQLException {
        for (int i = 0; i < CONNECTION_COUNT; i++) {
            Connection connection = ConnectionCreator.getConnection();
            connections.add(connection);
        }
    }
}
