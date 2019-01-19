package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.BookService;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String bookIdParameter = req.getParameter("bookId");
        Long bookId = null;
        if (bookIdParameter != null && !bookIdParameter.isEmpty()) {
            bookId = Long.parseLong(bookIdParameter);
        }

        String title = req.getParameter("bookTitle");
        int count = Integer.parseInt(req.getParameter("bookCount"));
        int year = Integer.parseInt(req.getParameter("bookYear"));

        Long authorId = Long.parseLong(req.getParameter("selectedAuthorId"));
        Author author = new Author(authorId);

        Long genreId = Long.parseLong(req.getParameter("selectedGenreId"));
        BookGenre bookGenre = new BookGenre(genreId);


        Book book = new Book(bookId, title, year, count, author, bookGenre);





        BookService bookService = new BookService();
        bookService.save(book);

        return CommandResult.redirect("/controller?command=addBookWindow&save=success");
    }
}
