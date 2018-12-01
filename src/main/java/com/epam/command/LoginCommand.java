package com.epam.command;

import com.epam.logic.UserService;
import com.epam.model.Employee;
import com.epam.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
     /*   UserService service = new UserService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<Employee> user = service.login(login, password);
        user.ifPresent(u -> req.setAttribute("user", u));
        return "/WEB_INF/index.jsp";*/

        HttpSession session = req.getSession(true);
        session.setAttribute("user", new User("Vitya", "admin"));

        return CommandResult.forward("/WEB-INF/pages/main.jsp");

    }
}
