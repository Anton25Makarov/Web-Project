package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetLibrariansCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        EmployeeService employeeService = new EmployeeService();
        List<Employee> librarians = employeeService.getLibrarians();

        req.setAttribute("librarians", librarians);

        return CommandResult.forward(JspPageName.ADMIN_LIBRARIANS_PAGE);
    }
}
