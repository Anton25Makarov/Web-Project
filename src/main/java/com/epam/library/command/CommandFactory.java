package com.epam.library.command;

public class CommandFactory {
    public static Command create(String command) { // class for commands
        switch (command) {
            case "login":
                return new LoginCommand();
            case "addBookWindow":
                return new GetBooksCommand();
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
            case "getLibrariansWindow":
                return new GetLibrariansCommand();
            case "saveLibrarian":
                return new SaveLibrarianCommand();
            case "removeLibrarian":
                return new RemoveLibrarianCommand();
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
