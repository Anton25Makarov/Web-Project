package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Author;
import com.epam.library.model.BookGenre;
import com.epam.library.service.EmployeeService;
import com.epam.library.model.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AddAuthorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String authorBookName = req.getParameter("authorBookName");
        String authorBookSurname = req.getParameter("authorBookSurname");

        Author author = new Author(authorBookName, authorBookSurname);

        try (EmployeeService employeeService = new EmployeeService()) {
            boolean result = employeeService.saveAuthor(author);

            if (result) {
                req.setAttribute("insertAuthorInfo", "Inserting is successful");
            } else {
                req.setAttribute("insertAuthorInfo", "Inserting is failed");
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
