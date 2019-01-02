<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>
<div>
    <h1>
        <fmt:message bundle="${loc}" key="header.title"/>
    </h1>
</div>
<%--
<br/>
<i>Language</i>
<a href="${pageContext.servletContext.contextPath}/controller?command=logOut">log out</a>
<br>--%>
