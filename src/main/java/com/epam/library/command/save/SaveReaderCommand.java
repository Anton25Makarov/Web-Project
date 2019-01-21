package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveReaderCommand implements Command {
    private static final int MIN_LOGIN_LENGTH = 4;
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String WRONG_LOGIN_KEY = "answer.wrong.login";
    private static final String SAVE_SUCCESSFUL_KEY = "answer.info.save.success";


    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String telephone = req.getParameter("telephoneNumber");

        HttpSession session = req.getSession();

        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNull(name, surname, login, password, telephone)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=getReadersWindow");
        }

        if (login.length() < MIN_LOGIN_LENGTH) {
            session.setAttribute("correctLoginInfo", rb.getString(WRONG_LOGIN_KEY));
            return CommandResult.redirect("/controller?command=getReadersWindow");
        }

        Reader reader = new Reader(name, surname, login, password, telephone);

        EmployeeService employeeService = new EmployeeService();
        ReaderService readerService = new ReaderService();

        if (employeeService.isEmployeeExist(login) || readerService.isReaderExist(login)) {
            return CommandResult.redirect("/controller?command=getReadersWindow");//page - const
        } else {
            readerService.save(reader);
        }

        session.setAttribute("saveStatusInfo", rb.getString(SAVE_SUCCESSFUL_KEY));
        return CommandResult.redirect("/controller?command=getReadersWindow");
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
