package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Book;
import com.epam.library.model.Order;
import com.epam.library.model.Reader;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class GetOrderedBooksCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("user");
        Long readerId = reader.getId();

        try  {
            ReaderService service = new ReaderService();
            List<Order> orders = service.getReaderOrders(readerId);

            for (Order order : orders) {
                Optional<Book> book = service.getBook(order.getBook().getId());
                book.ifPresent(order::setBook);
            }

            req.setAttribute("orders", orders);
        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/reader/books.jsp");
    }

//    private void setBooks

}
