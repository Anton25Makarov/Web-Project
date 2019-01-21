package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.jsp.JspPageName;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetOrdersToIssueCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        OrderService orderService = new OrderService();
        List<Order> orders = orderService.getOrdersToIssue();

        BookService bookService = new BookService();
        ReaderService readerService = new ReaderService();
        for (Order order : orders) {
            Optional<Book> book = bookService.getBook(order.getBook().getId());
            book.ifPresent(order::setBook);

            Optional<Reader> reader = readerService.getReader(order.getReader().getId());
            reader.ifPresent(order::setReader);
        }

        req.setAttribute("orders", orders);

        return CommandResult.forward(JspPageName.LIBRARIAN_ISSUE_ORDERS_PAGE);
    }
}
