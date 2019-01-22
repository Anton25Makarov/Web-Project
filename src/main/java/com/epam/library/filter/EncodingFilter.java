package com.epam.library.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(EncodingFilter.class.getName());
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            String codeRequest = request.getCharacterEncoding();
            if (code != null && !code.equalsIgnoreCase(codeRequest)) {
                request.setCharacterEncoding(code);
                response.setCharacterEncoding(code);
            }
            chain.doFilter(request, response);
        } catch (ServletException | IOException e) {
            LOGGER.error("Cannot set language encoding", e);
            throw new RuntimeException("Cannot set language encoding", e);
        }
    }

    @Override
    public void destroy() {
        code = null;
    }
}
