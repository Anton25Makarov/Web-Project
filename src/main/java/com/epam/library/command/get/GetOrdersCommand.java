package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.LibrarianService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GetOrdersCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try {
            LibrarianService service = new LibrarianService();
            List<Order> orders = service.takeOrders();

            for (Order order : orders) {
                Optional<Book> book = service.getBook(order.getBook().getId());
                book.ifPresent(order::setBook);

                Optional<Reader> reader = service.getReader(order.getReader().getId());
                reader.ifPresent(order::setReader);
            }
            req.setAttribute("orders", orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/librarian/all-orders.jsp");
    }
}
