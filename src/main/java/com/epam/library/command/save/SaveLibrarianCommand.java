package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SaveLibrarianCommand implements Command {
    public static final boolean IS_NOT_ADMIN = false;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Employee employee = new Employee(name, surname, login, password, IS_NOT_ADMIN);

        try (EmployeeService employeeService = new EmployeeService()) {
            if (employeeService.isEmployeeExist(login) || employeeService.isReaderExist(login)) {
                System.out.println("Exist");
                return CommandResult.redirect("/controller?command=getLibrariansWindow&save=fail");//page - const
            } else {
                System.out.println("Not exist");
                employeeService.saveEmployee(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return CommandResult.redirect("/controller?command=getLibrariansWindow&save=success");//page - const

    }
}
