package com.epam.library.command;

import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {

    /**
     * @param req
     * @param resp
     * @return
     * @throws ServiceException
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
