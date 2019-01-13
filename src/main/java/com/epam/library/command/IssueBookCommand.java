package com.epam.library.command;

import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.LibrarianService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Date;

public class IssueBookCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        String orderIdParameter = req.getParameter("orderId");

        Long orderId = null;
        if (orderIdParameter != null && !orderIdParameter.isEmpty()) {
            orderId = Long.parseLong(orderIdParameter);
        }

        boolean inReadingRoom = Boolean.parseBoolean(req.getParameter("orderInReadingRoom"));
//        String takingDateParameter = req.getParameter("orderTakingDate");
//        String returnDateParameter = req.getParameter("orderReturnDate");
        Long bookId = Long.valueOf(req.getParameter("orderBookId"));
        Long readerId = Long.valueOf(req.getParameter("orderReaderId"));

        Date takingDate = new Date();
        Book book = new Book(bookId);
        Reader reader = new Reader(readerId);


        Order order = new Order(orderId, inReadingRoom, takingDate, null, book, reader);

        try (LibrarianService service = new LibrarianService()) {

            service.saveOrder(order);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=getOrdersToIssue&save=success");//page - const
    }
}
