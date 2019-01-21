package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.model.Book;
import com.epam.library.service.BookService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetChosenBooksCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String authorName = req.getParameter("authorName");
        String authorSurname = req.getParameter("authorSurname");
        String bookTitle = req.getParameter("BookTitle");
        String bookGenre = req.getParameter("bookGenre");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNotNull(authorName, authorSurname, bookTitle, bookGenre)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.forward(JspPageName.READER_FIND_BOOKS_PAGE);
        }

        BookService bookService = new BookService();
        List<Book> books = bookService.getBooksInStoke(bookTitle, authorName, authorSurname, bookGenre);

        req.setAttribute("books", books);

        return CommandResult.forward(JspPageName.READER_FIND_BOOKS_PAGE);
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
