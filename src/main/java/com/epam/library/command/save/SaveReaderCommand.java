package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveReaderCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephoneNumber");

        Reader reader = new Reader(name, surname, login, password, telephone);


        EmployeeService employeeService = new EmployeeService();
        ReaderService readerService = new ReaderService();

        if (employeeService.isEmployeeExist(login) || readerService.isReaderExist(login)) {
            return CommandResult.redirect("/controller?command=getReadersWindow&save=fail");//page - const
        } else {
            readerService.save(reader);
        }

        return CommandResult.redirect("/controller?command=getReadersWindow&save=success");//page - const
    }
}
