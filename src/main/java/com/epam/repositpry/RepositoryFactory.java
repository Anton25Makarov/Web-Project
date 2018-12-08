package com.epam.repositpry;

import java.sql.Connection;

public class RepositoryFactory {
    public static EmployeeRepository createEmployeeRepository(Connection connection) {
        return new EmployeeRepository(connection);
    }

    public static ReaderRepository createReaderRepository(Connection connection) {
        return new ReaderRepository(connection);
    }
}