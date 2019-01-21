package com.epam.library.command.remove;

import com.epam.library.util.StringUtil;
import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveBookCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String REMOVE_SUCCESSFUL_KEY = "answer.info.remove.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String bookIdParameter = req.getParameter("bookId");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.isNumber(bookIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=addBookWindow");
        }

        Long bookId = Long.valueOf(bookIdParameter);

        Book book = new Book(bookId);

        BookService bookService = new BookService();
        bookService.remove(book);

        session.setAttribute("removeStatusInfo", rb.getString(REMOVE_SUCCESSFUL_KEY));
        return CommandResult.redirect("/controller?command=addBookWindow");
    }

    private ResourceBundle getResourceBundle(HttpSession session) {
        Object localParameter = session.getAttribute("local");
        Locale currentLang;
        if (localParameter != null) {
            String string = String.valueOf(localParameter);
            String[] langParameters = string.split("_");
            currentLang = new Locale(langParameters[0], langParameters[1]);
        } else {
            currentLang = new Locale(DEFAULT_LANG);
        }
        return ResourceBundle.getBundle("locale", currentLang);
    }
}