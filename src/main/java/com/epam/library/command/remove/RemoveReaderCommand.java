package com.epam.library.command.remove;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Reader;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RemoveReaderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {


        Long readerId = Long.parseLong(req.getParameter("readerId"));

        Reader reader = new Reader(readerId);




        ReaderService readerService = new ReaderService();
        readerService.remove(reader);

        return CommandResult.redirect("/controller?command=getReadersWindow&save=success");
    }
}
