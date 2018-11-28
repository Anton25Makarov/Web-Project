package com.epam.servlet;

import com.epam.logic.Command;
import com.epam.logic.CommandFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LibraryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        String command = req.getParameter("command");
        Command action = CommandFactory.create(command);
        String page = null;
        try {
            page = action.execute(req, resp);
        } catch (Exception e) { // more type exceptions
            req.setAttribute("error msg", e.getMessage());
            page = "/error.jsp";
        }
        try {
            dispatch(req, resp, page);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(req, resp);
    }

}
