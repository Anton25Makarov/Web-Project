package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class ChangeLanguageCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String LOCALE_ATTRIBUTE_NAME = "local";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();

        String lang = req.getParameter("lang");
        String page = req.getParameter("page");

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNull(lang, page)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.forward(JspPageName.LOGIN_PAGE);
        }


        switch (lang) { //!!!!!!!!!!!!!!!!
            case "en_gb":
                session.setAttribute(LOCALE_ATTRIBUTE_NAME, "en_GB");
                break;
            case "ru_ru":
                session.setAttribute(LOCALE_ATTRIBUTE_NAME, "ru_RU");
                break;
            case "be_by":
                session.setAttribute(LOCALE_ATTRIBUTE_NAME, "be_BY");
                break;
            default:
                throw new UnsupportedOperationException("Unknown language: " + lang);
        }

        switch (page) {
            case "login":
                return CommandResult.forward(JspPageName.LOGIN_PAGE);
            case "main":
                return CommandResult.forward(JspPageName.MAIN_PAGE);
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
        }
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
