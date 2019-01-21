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
    private static final Logger LOGGER = LogManager.getLogger(LibraryController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String command = req.getParameter("command");
//        Command action = CommandFactory.create(command);
//        action.execute(req, resp); // him
////        String page = null; // my
//        CommandResult page = null;
//        try {
//            page = action.execute(req, resp);
//        } catch (Exception e) { // more type exceptions
//            req.setAttribute("error msg", e.getMessage());
////            page = "/error.jsp"; // my
//            page = CommandResult.forward("/error.jsp");
//        }
//      /*  try {
//            dispatch(req, resp, page);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//        req.getRequestDispatcher(action.getPage()).forward(req, resp);
        String command = req.getParameter("command");

        // command = null - Обработать
        Command action = CommandFactory.create(command);

        CommandResult page = null;
        try {
            page = action.execute(req, resp);
        } catch (ServiceException e) {
            LOGGER.error(e);
        }

        dispatch(req, resp, page);
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
