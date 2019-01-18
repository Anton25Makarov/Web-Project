package com.epam.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        session.removeAttribute("user");
        session.removeAttribute("role");

        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
