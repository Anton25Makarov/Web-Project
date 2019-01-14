package com.epam.library.command;

import com.epam.library.command.get.*;
import com.epam.library.command.remove.RemoveBookCommand;
import com.epam.library.command.remove.RemoveLibrarianCommand;
import com.epam.library.command.remove.RemoveReaderCommand;
import com.epam.library.command.save.*;

public class CommandFactory {
    public static Command create(String command) { // class for commands
        switch (command) {
            case "login":
                return new LoginCommand();
            case "addBookWindow":
                return new GetBooksAuthorsGenresCommand();
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
            case "getReadersWindow":
                return new GetReadersCommand();
            case "saveReader":
                return new SaveReaderCommand();
            case "removeReader":
                return new RemoveReaderCommand();
            case "getAllOrders":
                return new GetOrdersCommand();
            case "issueBook":
                return new IssueBookCommand();
            case "getOrdersToIssue":
                return new GetOrdersToIssueCommand();
            case "readersAllBooks":
                return new GetBooksCommand();
            case "readerSelectBook":
                return new GetChosenBooksCommand();
            case "readerOrderBook":
                return new OrderBookCommand();
            case "readersBooks":
                return new GetOrderedBooksCommand();
            case "readerReturnBook":
                return new ReturnBookCommand();
            default:
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
