<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>
        <fmt:message bundle="${loc}" key="tab.title.error"/>
    </title>
</head>
<body>
<br>
Service
<h1>
    <fmt:message bundle="${loc}" key="label.error.runTime.description"/>
</h1>

<a href="${pageContext.servletContext.contextPath}/index.jsp">
    <fmt:message bundle="${loc}" key="label.backToStartPage"/>
</a>

</body>
</html>