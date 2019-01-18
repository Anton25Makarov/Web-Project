package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetLibrariansCommand implements Command {
    public static final boolean IS_NOT_ADMIN = false;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        try {
            EmployeeService employeeService = new EmployeeService();
            List<Employee> librarians = employeeService.takeLibrarians();

            req.setAttribute("librarians", librarians);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/admin/librarians.jsp");

    }
}
