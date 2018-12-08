package com.epam.repositpry;

public class RepositoryFactory {
    public static EmployeeRepository createEmployeeRepository() {
        return new EmployeeRepository();
    }

    public static ReaderRepository createReaderRepository() {
        return new ReaderRepository();
    }

}