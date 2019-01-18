package com.epam.library.command.remove;

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

public class RemoveBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        Long bookId = Long.parseLong(req.getParameter("bookId"));

        Book book = new Book(bookId);

        try  {
            EmployeeService employeeService = new EmployeeService();
            employeeService.removeBook(book);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return CommandResult.redirect("/controller?command=addBookWindow&save=success");

//        return CommandResult.forward("/WEB-INF/pages/books.jsp");
    }
}
