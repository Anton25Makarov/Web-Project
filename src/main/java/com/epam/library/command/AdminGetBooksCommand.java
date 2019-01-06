package com.epam.library.command;

import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AdminGetBooksCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try (EmployeeService employeeService = new EmployeeService()) {
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
