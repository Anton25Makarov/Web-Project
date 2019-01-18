<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=getAllOrders">
    <fmt:message bundle="${loc}" key="label.orders"/>
</a>
<a class="menu-ref" href="${pageContext.servletContext.contextPath}/controller?command=getOrdersToIssue">
    <fmt:message bundle="${loc}" key="label.issue.book"/>
</a>