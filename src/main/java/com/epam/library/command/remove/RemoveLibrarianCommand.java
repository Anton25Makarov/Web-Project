package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveLibrarianCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        Long librarianId = Long.parseLong(req.getParameter("librarianId"));

        Employee employee = new Employee(librarianId);




        EmployeeService employeeService = new EmployeeService();
        employeeService.remove(employee);

        return CommandResult.redirect("/controller?command=getLibrariansWindow&save=success");
    }
}
