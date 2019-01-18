<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=readersAllBooks">
    <fmt:message bundle="${loc}" key="label.find.book"/>
</a>

<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=readersBooks">
    <fmt:message bundle="${loc}" key="label.myBooks"/>
</a>

