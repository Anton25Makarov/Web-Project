package com.epam.library.command;

public class CommandFactory {
    public static Command create(String command) {
        switch (command) {
            case "login":
                return new LoginCommand();
            case "addBookWindow":
                return new AdminGetBooksCommand();
            case "saveBook":
                return new SaveBookCommand();
            case "addGenre":
                return new AddGenreCommand();
            case "addAuthor":
                return new AddAuthorCommand();
            case "removeBook":
                return new RemoveBookCommand();
            case "changeLanguage":
                return new ChangeLanguageCommand();
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
