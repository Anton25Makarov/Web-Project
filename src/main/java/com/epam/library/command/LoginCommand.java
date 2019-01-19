package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Employee;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        // class stringUtils - там проверка на login == null || login.isEmpty


        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("role");


        EmployeeService employeeService = new EmployeeService();
        Optional<Employee> employee = employeeService.login(login, password);
        if (employee.isPresent()) {
            session.setAttribute("role", "employee");
            session.setAttribute("user", employee.get());
            return CommandResult.forward("/WEB-INF/pages/main.jsp");
        }

        ReaderService readerService = new ReaderService();
        Optional<Reader> reader = readerService.login(login, password);
        if (reader.isPresent()) {
            session.setAttribute("role", "reader");
            session.setAttribute("user", reader.get());
            return CommandResult.forward("/WEB-INF/pages/main.jsp");
        }

        req.setAttribute("errorLogin", "Wrong login or password");
        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
