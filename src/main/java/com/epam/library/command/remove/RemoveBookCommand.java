package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        Long bookId = Long.parseLong(req.getParameter("bookId"));

        Book book = new Book(bookId);


        BookService bookService = new BookService();
        bookService.remove(book);

        return CommandResult.redirect("/controller?command=addBookWindow&save=success");
    }

}