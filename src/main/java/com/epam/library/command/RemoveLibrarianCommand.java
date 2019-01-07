package com.epam.library.command;

import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class RemoveLibrarianCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        Long librarianId = Long.parseLong(req.getParameter("librarianId"));

        Employee employee = new Employee(librarianId);

        try (EmployeeService employeeService = new EmployeeService()) {
            boolean result = employeeService.removeLibrarian(employee);

            if (result) {
                return CommandResult.redirect("/controller?command=getLibrariansWindow&save=success");
            } else {
                return CommandResult.redirect("/controller?command=getLibrariansWindow&save=fail");
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=getLibrariansWindow&save=fail");
    }
}
