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

public class GetBooksAuthorsGenresCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        BookService bookService = new BookService();
        List<Book> books = bookService.getBooks();
        req.setAttribute("books", books);

        BookGenreService genreService = new BookGenreService();
        List<BookGenre> genres = genreService.getGenres();
        req.setAttribute("genres", genres);

        AuthorService authorService = new AuthorService();
        List<Author> authors = authorService.getAuthors();
        req.setAttribute("authors", authors);

        return CommandResult.forward(JspPageName.ADMIN_BOOKS_PAGE);
    }
}
