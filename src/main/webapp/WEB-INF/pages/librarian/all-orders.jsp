<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="customTag" prefix="ctg" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>


<html>
<head>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/table.css'/>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/jquery-3.3.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/jquery-datatable.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/table.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/hideInfo.js"></script>

    <title>Menu</title>
</head>
<body>
<div id="head">
    <header>
        <jsp:include page="../../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../../fragments/nav-language.jsp">
                <jsp:param name="page" value="allOrders"/>
            </jsp:include>
            <jsp:include page="../../fragments/nav-logout.jsp"/>
        </ul>
    </nav>
</div>
<main>
    <aside>
        <div class="menu">
            <c:choose>
                <c:when test="${sessionScope.role == 'employee' and sessionScope.user.admin}">
                    <jsp:include page="../../fragments/admin/admin-menu.jsp"/>
                </c:when>
                <c:when test="${sessionScope.role == 'employee' and !sessionScope.user.admin}">
                    <jsp:include page="../../fragments/librarian/librarian-menu.jsp"/>
                </c:when>
                <c:when test="${sessionScope.role == 'reader'}">
                    <jsp:include page="../../fragments/reader/reader-menu.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </aside>

    <article>
        <div>
            <p class="page-title">
                <fmt:message bundle="${loc}" key="label.orders"/>
            </p>
            <table id="table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.inReadingRoom"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.date.issued"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.date.return"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.book"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.reader"/>
                    </th>
                </tr>
                </thead>
                <ctg:tableBody objects="${requestScope.orders}"/>
            </table>
        </div>
    </article>
</main>
</body>
</html>