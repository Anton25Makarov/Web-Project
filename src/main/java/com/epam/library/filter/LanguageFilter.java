package com.epam.library.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LanguageFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

       /* HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;

        String lang = request.getParameter("lang");
        if (lang != null) {
            HttpSession session = req.getSession();
            switch (lang) {
                case "en_gb":
                    session.setAttribute("local", "en_GB");
                    break;
                case "ru_ru":
                    session.setAttribute("local", "ru_RU");
                    break;
            }
//            req.getRequestDispatcher(req.getContextPath()).forward(request, response);
            resp.sendRedirect(req.getRequestURI());
            System.out.println("hello");
            return;
        }


        chain.doFilter(request, response);

        //        chain.doFilter(request, response);*/
    }

    @Override
    public void destroy() {
    }
}
