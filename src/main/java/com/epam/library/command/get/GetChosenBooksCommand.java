package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;
import com.epam.library.service.EmployeeService;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class GetChosenBooksCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {

        String authorName = req.getParameter("authorName");
        String authorSurname = req.getParameter("authorSurname");
        String bookTitle = req.getParameter("BookTitle");
        String bookGenre = req.getParameter("bookGenre");

        try  {
            ReaderService service = new ReaderService();
            List<Book> books = service.getBooks(bookTitle, authorName, authorSurname, bookGenre);

            req.setAttribute("books", books);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return CommandResult.forward("/WEB-INF/pages/reader/find-books.jsp");
    }

}
