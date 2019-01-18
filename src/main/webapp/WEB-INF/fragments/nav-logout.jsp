<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<li>
    <a href="${pageContext.servletContext.contextPath}/controller?command=logOut">
        <fmt:message bundle="${loc}" key="label.logOut"/>
    </a>
</li>