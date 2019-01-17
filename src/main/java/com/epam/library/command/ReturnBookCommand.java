package com.epam.library.command;

import com.epam.library.model.*;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

public class ReturnBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        Long bookId = Long.valueOf(req.getParameter("bookId"));
        Long orderId = Long.valueOf(req.getParameter("orderId"));

        HttpSession session = req.getSession();

        try  {
            ReaderService service = new ReaderService();
            Reader reader = (Reader) session.getAttribute("user");

            Optional<Book> book = service.getBook(bookId);

            Optional<Order> order = service.getOrder(orderId);


            if (book.isPresent() && order.isPresent()) {
                int count = book.get().getCount();
                book.get().setCount(count + 1);
                service.saveBook(book.get());

//                Order order = new Order(inReadingRoom, null, null, book.get(), reader);
                order.get().setReturnDate(new Date());

                service.saveOrder(order.get());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=readersBooks&save=success");//page - const
    }
}
