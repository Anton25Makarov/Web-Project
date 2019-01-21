package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.OrderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class IssueBookCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String SUCCESSFUL_BOOK_ISSUED_KEY = "answer.info.issue.book.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String orderIdParameter = req.getParameter("orderId");
        String bookIdParameter = req.getParameter("orderBookId");
        String readerIdParameter = req.getParameter("orderReaderId");
        String inReadingRoomParameter = req.getParameter("orderInReadingRoom");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNumbers(orderIdParameter, bookIdParameter, readerIdParameter) ||
                !stringUtil.isBoolean(inReadingRoomParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=getOrdersToIssue");
        }

        Long orderId = Long.parseLong(orderIdParameter);
        Long bookId = Long.parseLong(bookIdParameter);
        Long readerId = Long.parseLong(readerIdParameter);
        boolean inReadingRoom = Boolean.parseBoolean(inReadingRoomParameter);

        Date takingDate = new Date();
        Book book = new Book(bookId);
        Reader reader = new Reader(readerId);

        Order order = new Order(orderId, inReadingRoom, takingDate, null, book, reader);

        OrderService orderService = new OrderService();
        orderService.save(order);

        session.setAttribute("bookIssuedInfo", rb.getString(SUCCESSFUL_BOOK_ISSUED_KEY));
        return CommandResult.redirect("/controller?command=getOrdersToIssue");
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
