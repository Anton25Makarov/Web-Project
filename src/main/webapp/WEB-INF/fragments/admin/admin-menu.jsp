<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=addBookWindow">
    <fmt:message bundle="${loc}" key="label.books"/>
</a>
<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=getLibrariansWindow">
    <fmt:message bundle="${loc}" key="label.librarians"/>
</a>
<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=getReadersWindow">
    <fmt:message bundle="${loc}" key="label.readers"/>
</a>
