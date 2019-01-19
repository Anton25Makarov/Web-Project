package com.epam.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();

        String lang = req.getParameter("lang");
        if (lang != null) {
            switch (lang) {
                case "en_gb":
                    session.setAttribute("local", "en_GB");
                    break;
                case "ru_ru":
                    session.setAttribute("local", "ru_RU");
                    break;
                case "be_by":
                    session.setAttribute("local", "be_BY");
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown language: " + lang);
            }
        }

        String page = req.getParameter("page");

        if (page != null) {
            switch (page) {
                case "login":
                    return CommandResult.forward("/WEB-INF/pages/login.jsp");
                case "main":
                    return CommandResult.forward("/WEB-INF/pages/main.jsp");
                case "addBooks":
                    return CommandResult.redirect("/controller?command=addBookWindow");
                case "addLibrarians":
                    return CommandResult.redirect("/controller?command=getLibrariansWindow");
                case "addReaders":
                    return CommandResult.redirect("/controller?command=getReadersWindow");
                case "allOrders":
                    return CommandResult.redirect("/controller?command=getAllOrders");
                case "issueOrders":
                    return CommandResult.redirect("/controller?command=getOrdersToIssue");
                case "readersBooks":
                    return CommandResult.redirect("/controller?command=readersBooks");
                case "readerFindBook":
                    return CommandResult.redirect("/controller?command=readersAllBooks");
                default:
                    throw new UnsupportedOperationException("Unknown page: " + page);
            }//
        }

        return CommandResult.redirect("Error page");
    }
}
