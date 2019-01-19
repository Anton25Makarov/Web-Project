package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Author;
import com.epam.library.model.BookGenre;
import com.epam.library.model.Book;
import com.epam.library.service.BookGenreService;
import com.epam.library.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class SaveGenreCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        String bookGenre = req.getParameter("bookGenre");

        BookGenre genre = new BookGenre(bookGenre);


        BookGenreService genreService = new BookGenreService();
        genreService.save(genre);

        return CommandResult.redirect("/controller?command=addBookWindow&save=success");
    }
}
