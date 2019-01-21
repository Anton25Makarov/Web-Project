package com.epam.library.controller;

import com.epam.library.command.Command;
import com.epam.library.command.CommandFactory;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryController extends HttpServlet {
    private static final long serialVersionUID = -1550596778941759867L;
    private static final String PARAMETER_COMMAND = "command";
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ERROR_PAGE = "";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.error("Hello doGet");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter(PARAMETER_COMMAND);

        Command action = CommandFactory.create(command);

        try {
            CommandResult page = action.execute(req, resp);
            dispatch(req, resp, page);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();

        if (page.isRedirect()) {
            resp.sendRedirect(req.getContextPath() + pageToDispatch);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageToDispatch);
            dispatcher.forward(req, resp);
        }
    }
}
