package com.epam.library.command.save;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Author;
import com.epam.library.service.AuthorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveAuthorCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        String authorBookName = req.getParameter("authorBookName");
        String authorBookSurname = req.getParameter("authorBookSurname");

        Author author = new Author(authorBookName, authorBookSurname);



        AuthorService authorService = new AuthorService();
        authorService.save(author);

        return CommandResult.redirect("/controller?command=addBookWindow&save=success");
    }
}
