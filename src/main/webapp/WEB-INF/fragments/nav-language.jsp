<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<li>
    <a>
        <fmt:message bundle="${loc}" key="label.language.title"/>
    </a>
    <ul>
        <li>
            <a href="${pageContext.servletContext.contextPath}/?lang=en_gb">
                <fmt:message bundle="${loc}" key="label.language.en.uk"/>
            </a>
        </li>
        <li>
            <a href="?lang=ru_ru">
                <fmt:message bundle="${loc}" key="label.language.ru.ru"/>
            </a>
        </li>
    </ul>
</li>
