package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class GetOrderedBooksCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("user");
        Long readerId = reader.getId();

        OrderService orderService = new OrderService();
        List<Order> orders = orderService.getReaderOrders(readerId);

        BookService bookService = new BookService();
        for (Order order : orders) {
            Optional<Book> book = bookService.getBook(order.getBook().getId());
            book.ifPresent(order::setBook);
        }

        req.setAttribute("orders", orders);

        return CommandResult.forward("/WEB-INF/pages/reader/books.jsp");
    }

}
