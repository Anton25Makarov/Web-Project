package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GetBooksCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try  {
            EmployeeService employeeService = new EmployeeService();
            List<Book> books = employeeService.takeBooksInStock();

            req.setAttribute("books", books);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/reader/find-books.jsp");
    }

}
