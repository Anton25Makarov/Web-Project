package com.epam.repositpry;

import java.io.IOException;

public class RepositoryFactory {
    public static EmployeeRepository createEmployeeRepository() throws IOException {
        return new EmployeeRepository();
    }

    public static ReaderRepository createReaderRepository() {
        return new ReaderRepository();
    }

}