package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Optional;

public class ReturnBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        Long bookId = Long.valueOf(req.getParameter("bookId"));
        Long orderId = Long.valueOf(req.getParameter("orderId"));


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

        return CommandResult.redirect("/controller?command=readersBooks&save=success");//page - const
    }
}
