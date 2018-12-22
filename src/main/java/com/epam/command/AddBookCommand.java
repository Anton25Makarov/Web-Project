package com.epam.command;

import com.epam.model.Book;
import com.epam.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class AddBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        EmployeeService employeeService = new EmployeeService();
        try {
            List<Book> books = employeeService.takeBooks();
            req.setAttribute("books", books);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CommandResult.forward("/WEB-INF/pages/admin-book.jsp");
    }
}
