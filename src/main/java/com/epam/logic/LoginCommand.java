package com.epam.logic;

import com.epam.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserService service = new UserService();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = service.login(login, password);
        user.ifPresent(u -> req.setAttribute("user", u));
        return "/WEB_INF/main.jsp";
    }
}
