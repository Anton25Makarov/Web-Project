package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;
import com.epam.library.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class OrderBookCommand implements Command {
    private static final String DEFAULT_LANG = "";
    private static final String WRONG_OPERATION_KEY = "answer.wrong.operation";
    private static final String SUCCESSFUL_ORDERED_BOOK_KEY = "answer.info.order.book.success";

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String bookIdParameter = req.getParameter("bookId");
        String inReadingRoomParameter = req.getParameter("inReadingRoom");

        HttpSession session = req.getSession();

        ResourceBundle rb = getResourceBundle(session);
        StringUtil stringUtil = new StringUtil();
        if (!stringUtil.isNumber(bookIdParameter)) {
            session.setAttribute("parametersInfo", rb.getString(WRONG_OPERATION_KEY));
            return CommandResult.redirect("/controller?command=getOrdersToIssue");
        }

        Long bookId = Long.parseLong(bookIdParameter);
        boolean inReadingRoom = !stringUtil.isNullOrEmpty(inReadingRoomParameter);


        Reader reader = (Reader) session.getAttribute("user");

        BookService bookService = new BookService();
        Optional<Book> book = bookService.getBookInStoke(bookId);

        if (book.isPresent()) {
            int count = book.get().getCount();
            book.get().setCount(count - 1);
            bookService.save(book.get());

            Order order = new Order(inReadingRoom, null, null, book.get(), reader);

            OrderService orderService = new OrderService();
            orderService.save(order);
        }

        session.setAttribute("bookOrderedInfo", rb.getString(SUCCESSFUL_ORDERED_BOOK_KEY));
        return CommandResult.redirect("/controller?command=readersAllBooks");//page - const
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
