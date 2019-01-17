package com.epam.library.command;

import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;
import com.epam.library.model.Employee;
import com.epam.library.model.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        // class stringUtils - там проверка на login == null || login.isEmpty
        HttpSession session = req.getSession();
        try  {
            EmployeeService employeeService = new EmployeeService();
            session.removeAttribute("user");
            session.removeAttribute("role");

            Optional<Employee> employee = employeeService.login(login, password);
            employee.ifPresent(e -> {
                session.setAttribute("role", "employee");
                session.setAttribute("user", e);
            });

            if (!employee.isPresent()) {
                ReaderService readerService = new ReaderService();
                Optional<Reader> reader = readerService.login(login, password);
                reader.ifPresent(r -> {
                    session.setAttribute("role", "reader");
                    session.setAttribute("user", r);
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (session.getAttribute("user") == null) {
            req.setAttribute("errorLogin", "Wrong login or password");
            return CommandResult.forward("/WEB-INF/pages/login.jsp");
        }

        return CommandResult.forward("/WEB-INF/pages/main.jsp");//page - const
    }
}
