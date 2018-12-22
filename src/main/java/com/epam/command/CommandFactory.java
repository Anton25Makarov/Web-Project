package com.epam.command;

public class CommandFactory {
    public static Command create(String command) {
        switch (command) {
            case "login":
                return new LoginCommand();
            case "addBookWindow":
                return new AddBookCommand();
            case "adminLibrarian":
                return new AdminLibrarianCommand();
            case "adminReaders":
                return new AdminReadersCommand();
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
