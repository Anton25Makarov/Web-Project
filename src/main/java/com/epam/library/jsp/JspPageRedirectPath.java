package com.epam.library.jsp;

public class JspPageRedirectPath {
    public static final String ADMIN_BOOKS_PAGE = "/controller?command=addBookWindow";
    public static final String ADMIN_LIBRARIANS_PAGE = "/controller?command=getLibrariansWindow";
    public static final String ADMIN_READERS_PAGE = "/controller?command=getReadersWindow";
    public static final String LIBRARIAN_ALL_ORDERS_PAGE = "/controller?command=getAllOrders";
    public static final String LIBRARIAN_ISSUE_ORDERS_PAGE = "/controller?command=getOrdersToIssue";
    public static final String READER_BOOKS_PAGE = "/controller?command=readersBooks";
    public static final String READER_FIND_BOOKS_PAGE = "/controller?command=readersAllBooks";

    private JspPageRedirectPath() {
    }
}
