package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Employee;
import com.epam.library.model.Reader;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class RemoveReaderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {


        Long readerId = Long.parseLong(req.getParameter("readerId"));

        Reader reader = new Reader(readerId);

        try (EmployeeService employeeService = new EmployeeService()) {

            employeeService.removeReader(reader);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.redirect("/controller?command=getReadersWindow&save=success");
    }
}
