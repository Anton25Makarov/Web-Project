package com.epam.library.command;

import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SaveBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        EmployeeService employeeService = new EmployeeService();

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

        try {
            boolean result = employeeService.saveBook(book);

            if (result) {
                req.setAttribute("insertBookInfo", "Inserting is successful");
            } else {
                req.setAttribute("insertBookInfo", "Inserting is failed");
            }

            List<Book> books = employeeService.takeBooks();
            req.setAttribute("books", books);

            List<BookGenre> genres = employeeService.takeGenres();
            req.setAttribute("genres", genres);

            List<Author> authors = employeeService.takeAuthors();
            req.setAttribute("authors", authors);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=addBookWindow&save=success");
//        return CommandResult.redirect("/WEB-INF/pages/admin-book.jsp");
//        return CommandResult.forward("/WEB-INF/pages/admin-book.jsp");
    }
}
