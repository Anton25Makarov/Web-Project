package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.model.Employee;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class LoginCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String AUTHORISATION_FAILED_KEY = "label.authorisation.fail";

    @Override

    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("role");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNotNull(login, password)) {
            req.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.forward(JspPageName.LOGIN_PAGE);
        }

        EmployeeService employeeService = new EmployeeService();
        Optional<Employee> employee = employeeService.login(login, password);
        if (employee.isPresent()) {
            session.setAttribute("role", "employee");
            session.setAttribute("user", employee.get());
            return CommandResult.forward(JspPageName.MAIN_PAGE);
        }

        ReaderService readerService = new ReaderService();
        Optional<Reader> reader = readerService.login(login, password);
        if (reader.isPresent()) {
            session.setAttribute("role", "reader");
            session.setAttribute("user", reader.get());
            return CommandResult.forward(JspPageName.MAIN_PAGE);
        }

        req.setAttribute("errorAuthorisation", rb.getString(AUTHORISATION_FAILED_KEY));
        return CommandResult.forward(JspPageName.LOGIN_PAGE);
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
