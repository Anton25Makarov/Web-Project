package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageRedirectPath;
import com.epam.library.model.Reader;
import com.epam.library.service.ReaderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveReaderCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String REMOVE_SUCCESSFUL_KEY = "answer.info.remove.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String readerIdParameter = req.getParameter("readerId");

        HttpSession session = req.getSession();

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.isNumber(readerIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect(JspPageRedirectPath.ADMIN_READERS_PAGE);
        }

        Long readerId = Long.valueOf(readerIdParameter);
        Reader reader = new Reader(readerId);

        ReaderService readerService = new ReaderService();
        readerService.remove(reader);

        session.setAttribute("removeStatusInfo", rb.getString(REMOVE_SUCCESSFUL_KEY));
        return CommandResult.redirect(JspPageRedirectPath.ADMIN_READERS_PAGE);
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
