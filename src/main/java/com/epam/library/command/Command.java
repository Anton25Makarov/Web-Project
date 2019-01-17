package com.epam.library.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public interface Command {
    /**
     *
     * @param req
     * @param resp
     * @return
     */
    CommandResult execute(HttpServletRequest req, HttpServletResponse resp);
}
