package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Order;
import com.epam.library.service.LibrarianService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetOrdersCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        try (LibrarianService service = new LibrarianService()) {
            List<Order> orders = service.takeOrders();

            req.setAttribute("orders", orders);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/librarian-order.jsp");
    }
}
