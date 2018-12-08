package com.epam.command;

import com.epam.service.EmployeeService;
import com.epam.service.ReaderService;
import com.epam.model.Employee;
import com.epam.model.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");


        try {
        HttpSession session = req.getSession();

            EmployeeService employeeService = new EmployeeService();
            Optional<Employee> employee = employeeService.login(login, password);
            employee.ifPresent(e -> session.setAttribute("employee", e));

            ReaderService readerService = new ReaderService();
            if (!employee.isPresent()) {
                Optional<Reader> reader = readerService.login(login, password);
                reader.ifPresent(r -> session.setAttribute("reader", r));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/admin-main.jsp"); // страничка одна, там case или if проверять
    }
}