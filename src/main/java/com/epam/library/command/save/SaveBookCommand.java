package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageRedirectPath;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.BookService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveBookCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String WRONG_NUMBER_KEY = "answer.wrong.number";
    private static final String SAVE_SUCCESSFUL_KEY = "answer.info.save.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String bookIdParameter = req.getParameter("bookId");
        String title = req.getParameter("bookTitle");
        String countParameter = req.getParameter("bookCount");
        String yearParameter = req.getParameter("bookYear");
        String genreIdParameter = req.getParameter("selectedGenreId");
        String authorIdParameter = req.getParameter("selectedAuthorId");

        HttpSession session = req.getSession();

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNullAndNotEmpty(title, yearParameter, countParameter) ||
                !stringUtil.areNumbers(authorIdParameter, genreIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect(JspPageRedirectPath.ADMIN_BOOKS_PAGE);
        }

        if (!stringUtil.areNumbers(countParameter, yearParameter)) {
            session.setAttribute("correctLoginInfo", rb.getString(WRONG_NUMBER_KEY));
            return CommandResult.redirect(JspPageRedirectPath.ADMIN_BOOKS_PAGE);
        }

        Long bookId = null;
        if (stringUtil.isNumber(bookIdParameter)) {
            bookId = Long.parseLong(bookIdParameter);
        }

        int count = Integer.parseInt(countParameter);
        int year = Integer.parseInt(yearParameter);
        Long genreId = Long.parseLong(genreIdParameter);
        Long authorId = Long.parseLong(authorIdParameter);

        Author author = new Author(authorId);

        BookGenre bookGenre = new BookGenre(genreId);
        Book book = new Book(bookId, title, year, count, author, bookGenre);


        BookService bookService = new BookService();
        bookService.save(book);

        session.setAttribute("saveStatusInfo", rb.getString(SAVE_SUCCESSFUL_KEY));
        return CommandResult.redirect(JspPageRedirectPath.ADMIN_BOOKS_PAGE);
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
