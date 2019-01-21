package com.epam.library.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
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
            throw new RuntimeException("Cannot set language encoding", e); //Log
        }
    }

    @Override
    public void destroy() {
        code = null;
    }
}
