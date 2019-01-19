package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

public class IssueBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String orderIdParameter = req.getParameter("orderId");

        Long orderId = null;
        if (orderIdParameter != null && !orderIdParameter.isEmpty()) {
            orderId = Long.parseLong(orderIdParameter);
        }

        boolean inReadingRoom = Boolean.parseBoolean(req.getParameter("orderInReadingRoom"));
        Long bookId = Long.valueOf(req.getParameter("orderBookId"));
        Long readerId = Long.valueOf(req.getParameter("orderReaderId"));

        Date takingDate = new Date();
        Book book = new Book(bookId);
        Reader reader = new Reader(readerId);


        Order order = new Order(orderId, inReadingRoom, takingDate, null, book, reader);



        OrderService orderService = new OrderService();
        orderService.save(order);

        return CommandResult.redirect("/controller?command=getOrdersToIssue&save=success");//page - const
    }
}
