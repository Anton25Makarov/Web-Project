package com.epam.library.tag;

import com.epam.library.model.Author;
import com.epam.library.model.Book;
import com.epam.library.model.BookGenre;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.jstl.fmt.LocaleSupport;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("serial")
public class TableTag extends TagSupport {
    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write("<tbody>");
            for (Book book : books) {
                Author author = book.getAuthor();
                BookGenre genre = book.getGenre();

                out.write("<tr>");

                out.write("<td>" + book.getId() + "</td>");
                out.write("<td>" + book.getTitle() + "</td>");
                out.write("<td>" + author.getName() + " " + author.getSurname() + "</td>");
                out.write("<td>" + book.getYear() + "</td>");
                out.write("<td>" + book.getCount() + "</td>");

                out.write("</tr>");
            }

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}