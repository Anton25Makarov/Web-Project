package com.epam.command;

import com.epam.model.Author;
import com.epam.model.Book;
import com.epam.model.BookGenre;
import com.epam.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class RemoveBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        EmployeeService employeeService = new EmployeeService();

        Long bookId = Long.parseLong(req.getParameter("bookId"));

        Book book = new Book(bookId);

        try {
            boolean result = employeeService.removeBook(book);

            if (result) {
                req.setAttribute("insertBookInfo", "Removing is successful");
            } else {
                req.setAttribute("insertBookInfo", "Removing is failed");
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

        return CommandResult.forward("/WEB-INF/pages/admin-book.jsp");
    }
}
