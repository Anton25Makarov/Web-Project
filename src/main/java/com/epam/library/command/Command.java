package com.epam.library.command;

import com.epam.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {

    /**
     * @param req to provide request information for HTTP servlets.
     * @param resp to provide HTTP-specific functionality in sending a response
     * @return an instance of {@link CommandResult}, that has a web-page name to show.
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException;
}
