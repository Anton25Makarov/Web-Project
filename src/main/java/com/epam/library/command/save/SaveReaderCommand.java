package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Employee;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class SaveReaderCommand implements Command {
    public static final boolean IS_NOT_ADMIN = false;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephoneNumber");

        Reader reader = new Reader(name, surname, login, password, telephone);

        try (EmployeeService employeeService = new EmployeeService()) {
            if (employeeService.isEmployeeExist(login) || employeeService.isReaderExist(login)) {
                System.out.println("Exist");
                return CommandResult.redirect("/controller?command=getReadersWindow&save=fail");//page - const
            } else {
                System.out.println("Not exist");
                employeeService.saveReader(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return CommandResult.redirect("/controller?command=getReadersWindow&save=success");//page - const

    }
}
