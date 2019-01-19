package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveLibrarianCommand implements Command {
    private static final boolean IS_ADMIN = false;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Employee librarian = new Employee(name, surname, login, password, IS_ADMIN);


        EmployeeService employeeService = new EmployeeService();
        ReaderService readerService = new ReaderService();
        if (employeeService.isEmployeeExist(login) || readerService.isReaderExist(login)) {
            return CommandResult.redirect("/controller?command=getLibrariansWindow&save=fail");
        } else {
            employeeService.save(librarian);
        }


        return CommandResult.redirect("/controller?command=getLibrariansWindow&save=success");//page - const

    }
}
