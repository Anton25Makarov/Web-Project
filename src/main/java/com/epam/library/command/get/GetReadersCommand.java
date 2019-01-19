package com.epam.library.command.get;

import com.epam.library.command.Command;
import com.epam.library.command.CommandResult;
import com.epam.library.exception.ServiceException;
import com.epam.library.model.Reader;
import com.epam.library.service.ReaderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetReadersCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        ReaderService readerService = new ReaderService();
        List<Reader> readers = readerService.getReaders();

        req.setAttribute("readers", readers);

        return CommandResult.forward("/WEB-INF/pages/admin/readers.jsp");

    }
}
