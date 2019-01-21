package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveLibrarianCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String WRONG_LOGIN_KEY = "answer.wrong.login";
    private static final String SAVE_SUCCESSFUL_KEY = "answer.info.save.success";
    private static final int MIN_LOGIN_LENGTH = 4;
    private static final boolean IS_ADMIN = false;

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        HttpSession session = req.getSession();


        StringUtil stringUtil = new StringUtil();
        ResourceBundle rb = getResourceBundle(session);
        if (!stringUtil.areNotNull(login, password, name, surname)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=getLibrariansWindow");
        }

        if (login.length() < MIN_LOGIN_LENGTH) {
            session.setAttribute("correctLoginInfo", rb.getString(WRONG_LOGIN_KEY));
            return CommandResult.redirect("/controller?command=getLibrariansWindow");
        }

        Employee librarian = new Employee(name, surname, login, password, IS_ADMIN);


        EmployeeService employeeService = new EmployeeService();
        ReaderService readerService = new ReaderService();
        if (employeeService.isEmployeeExist(login) || readerService.isReaderExist(login)) {
            return CommandResult.redirect("/controller?command=getLibrariansWindow");
        } else {
            employeeService.save(librarian);
        }


        session.setAttribute("saveStatusInfo", rb.getString(SAVE_SUCCESSFUL_KEY));
        return CommandResult.redirect("/controller?command=getLibrariansWindow");//page - const
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
