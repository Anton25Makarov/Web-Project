package com.epam.library.tag;

import com.epam.library.model.Order;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class TableBodyTag extends TagSupport {

    private List<Object> objects;

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            out.write("<tbody>");


            if (objects != null && !objects.isEmpty() && objects.get(0) instanceof Order) {
                writeBodyOrder();
            }

            out.write("</tbody>");

        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }

    private void writeBodyOrder() throws IOException {
        JspWriter out = pageContext.getOut();

        int i = 1;
        for (Object object : objects) {
            Order order = (Order) object;
            out.write("<tr>");
            out.write("<td>" + i++ + "</td>");

            if (order.isInReadingRoom()) {
                out.write("<td class='center-align'><input type='checkbox' checked disabled/></td>");
            } else {
                out.write("<td class='center-align'><input type='checkbox' disabled/></td>");
            }

            if (order.getTakingDate() == null) {
                out.write("<td class='center-align'> - </td>");
            } else {
                out.write("<td class='center-align'>" + order.getTakingDate() + "</td>");
            }
            if (order.getReturnDate() == null) {
                out.write("<td class='center-align'> - </td>");
            } else {
                out.write("<td class='center-align'>" + order.getReturnDate() + "</td>");
            }
            out.write("<td>" + order.getBook().getTitle() + "</td>");
            out.write("<td>" + order.getReader().getLogin() + "</td>");
            out.write("</tr>");
        }
    }
}