package com.epam.library.command;

import com.epam.library.exception.ServiceException;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.BookService;
import com.epam.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class OrderBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String bookIdParameter = req.getParameter("bookId");

        Long bookId = null;
        if (bookIdParameter != null && !bookIdParameter.isEmpty()) {
            bookId = Long.parseLong(bookIdParameter);
        }

        String inReadingRoomParameter = req.getParameter("inReadingRoom");
        boolean inReadingRoom = false;
        if (inReadingRoomParameter != null) {
            inReadingRoom = true;
        }





        HttpSession session = req.getSession();
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


        return CommandResult.redirect("/controller?command=readersAllBooks&save=success");//page - const
    }
}
