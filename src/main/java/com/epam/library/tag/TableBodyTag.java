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


            if (objects != null && objects.get(0) instanceof Order) {
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

        for (Object object : objects) {
            Order order = (Order) object;
            out.write("<tr>");
            out.write("<td>" + order.getId() + "</td>");

            if (order.isInReadingRoom()) {
                out.write("<td><input type='checkbox' checked disabled/></td>");
            } else {
                out.write("<td><input type='checkbox' disabled/></td>");
            }

//            out.write("<td>" + order.isInReadingRoom() + "</tr>");
            if (order.getTakingDate() == null) {
                out.write("<td> - </td>");
            } else {
                out.write("<td>" + order.getTakingDate() + "</td>");
            }
            if (order.getReturnDate() == null) {
                out.write("<td> - </td>");
            } else {
                out.write("<td>" + order.getReturnDate() + "</td>");
            }
//            out.write("<td>" + order.getReturnDate() + "</tr>");
            out.write("<td>" + order.getBook().getId() + "</td>");
            out.write("<td>" + order.getReader().getId() + "</td>");
            out.write("</tr>");

        }
    }
}