package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.BookGenre;
import com.epam.library.service.BookGenreService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveGenreCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String SAVE_SUCCESSFUL_KEY = "answer.info.save.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String bookGenre = req.getParameter("bookGenre");

        HttpSession session = req.getSession();

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (stringUtil.isNullOrEmpty(bookGenre)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=addBookWindow");
        }

        BookGenre genre = new BookGenre(bookGenre);

        BookGenreService genreService = new BookGenreService();
        genreService.save(genre);

        session.setAttribute("saveStatusInfo", rb.getString(SAVE_SUCCESSFUL_KEY));
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
