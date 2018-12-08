package com.epam.command;

import com.epam.logic.EmployeeService;
import com.epam.logic.ReaderService;
import com.epam.model.Employee;
import com.epam.model.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        EmployeeService employeeService = new EmployeeService();
        ReaderService readerService = new ReaderService();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        HttpSession session = req.getSession();

        Optional<Employee> employee = employeeService.login(login, password);
        employee.ifPresent(e -> session.setAttribute("employee", e));
//        employee.ifPresent(e -> req.setAttribute("employee", e));

        if (!employee.isPresent()) {
            Optional<Reader> reader = readerService.login(login, password);
            reader.ifPresent(r -> session.setAttribute("reader", r));
//            reader.ifPresent(r -> req.setAttribute("reader", r));
        }

        return CommandResult.forward("/WEB-INF/pages/admin-main.jsp"); // страничка одна, там case или if проверять
        //  HttpSession session = req.getSession(true);
        // session.setAttribute("user", new User("Vitya", "admin"));
//          return CommandResult.forward("/WEB-INF/pages/admin-main.jsp");
    }
}
