package com.epam.library.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class EmptyDisplayerTag extends TagSupport {
    private Object value;
    private String replace;

    public void setValue(Object value) {
        this.value = value;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();

            if (value == null) {
                out.write(replace);
            } else {
                out.write(value.toString());
            }
        } catch (IOException e) {
            throw new JspTagException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}