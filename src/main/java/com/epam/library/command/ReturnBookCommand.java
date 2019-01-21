package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReturnBookCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String SUCCESSFUL_RETURN_BOOK_KEY = "answer.info.return.book.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String bookIdParameter = req.getParameter("bookId");
        String orderIdParameter = req.getParameter("orderId");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.areNumbers(bookIdParameter, orderIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=readersBooks");
        }

        Long bookId = Long.valueOf(bookIdParameter);
        Long orderId = Long.valueOf(orderIdParameter);

        BookService bookService = new BookService();
        Optional<Book> book = bookService.getBook(bookId);

        OrderService orderService = new OrderService();
        Optional<Order> order = orderService.getOrder(orderId);


        if (book.isPresent() && order.isPresent()) {
            int count = book.get().getCount();
            book.get().setCount(count + 1);
            bookService.save(book.get());

            order.get().setReturnDate(new Date());
            orderService.save(order.get());
        }

        session.setAttribute("bookReturnInfo", rb.getString(SUCCESSFUL_RETURN_BOOK_KEY));
        return CommandResult.redirect("/controller?command=readersBooks");
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
