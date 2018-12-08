package com.epam.servlet;

import com.epam.command.Command;
import com.epam.command.CommandFactory;
import com.epam.command.CommandResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryServlet extends HttpServlet {

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

        Command action = CommandFactory.create(command);

        CommandResult page = action.execute(req, resp);

        dispatch(req, resp, page);
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, CommandResult page)
            throws ServletException, IOException {
        String pageToDispatch = page.getPage();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pageToDispatch);
        dispatcher.forward(req, resp);

    }
//name - controller
}
