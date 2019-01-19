package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetChosenBooksCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String authorName = req.getParameter("authorName");
        String authorSurname = req.getParameter("authorSurname");
        String bookTitle = req.getParameter("BookTitle");
        String bookGenre = req.getParameter("bookGenre");

        BookService bookService = new BookService();
        List<Book> books = bookService.getBooksInStoke(bookTitle, authorName, authorSurname, bookGenre);

        req.setAttribute("books", books);

        return CommandResult.forward("/WEB-INF/pages/reader/find-books.jsp");
    }

}
