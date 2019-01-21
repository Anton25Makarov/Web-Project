package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.AuthorService;
import com.epam.library.service.BookGenreService;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetBooksCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        BookService bookService = new BookService();
        List<Book> books = bookService.getBooksInStock();

        req.setAttribute("books", books);

        return CommandResult.forward(JspPageName.READER_FIND_BOOKS_PAGE);
    }

}
