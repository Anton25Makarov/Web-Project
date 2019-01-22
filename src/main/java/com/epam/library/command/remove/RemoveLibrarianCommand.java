package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageRedirectPath;
import com.epam.library.model.Employee;
import com.epam.library.service.EmployeeService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemoveLibrarianCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String REMOVE_SUCCESSFUL_KEY = "answer.info.remove.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String librarianIdParameter = req.getParameter("librarianId");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(req.getSession());
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.isNumber(librarianIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect(JspPageRedirectPath.ADMIN_LIBRARIANS_PAGE);
        }

        Long librarianId = Long.valueOf(librarianIdParameter);

        Employee employee = new Employee(librarianId);

        EmployeeService employeeService = new EmployeeService();
        employeeService.remove(employee);

        session.setAttribute("removeStatusInfo", rb.getString(REMOVE_SUCCESSFUL_KEY));
        return CommandResult.redirect(JspPageRedirectPath.ADMIN_LIBRARIANS_PAGE);
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
