package com.epam.library.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int CONNECTION_COUNT = 5;
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());


    private static ConnectionPool instance = new ConnectionPool();

    private Semaphore semaphore = new Semaphore(CONNECTION_COUNT, true);
    private Lock takeLock = new ReentrantLock();
    private Lock returnLock = new ReentrantLock();

    private Queue<Connection> connections = new ArrayDeque<>();

    private ConnectionPool() {
        init();
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection takeConnection() {
        try {
            semaphore.acquire();
            takeLock.lock();
            return connections.poll();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while waiting for a database connection.", e);
        } finally {
            takeLock.unlock();
        }
    }

    public void returnConnection(Connection connection) {
        try {
            returnLock.lock();
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
        } catch (IOException | SQLException | ClassNotFoundException e) {
            LOGGER.error(e);
            throw new RuntimeException("Can not create connection to database.", e);
        }
    }
}
