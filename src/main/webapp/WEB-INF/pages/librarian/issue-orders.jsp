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
            src="${pageContext.servletContext.contextPath}/resource/js/showInfo.js"></script>

    <title>
        <fmt:message bundle="${loc}" key="tab.title.library"/>
    </title>
</head>
<body>
<div id="head">
    <header>
        <jsp:include page="../../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../../fragments/nav-language.jsp">
                <jsp:param name="page" value="issueOrders"/>
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
        <div class="server-answer">
            <p class="infos">${sessionScope.parametersInfo}</p>
            <c:remove var="parametersInfo" scope="session"/>
            <p class="infos">${sessionScope.bookIssuedInfo}</p>
            <c:remove var="bookIssuedInfo" scope="session"/>
        </div>
    </aside>

    <article>
        <div>
            <p class="page-title">
                <fmt:message bundle="${loc}" key="label.applicationsForBooksIssuance"/>
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
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${requestScope.orders}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td class='center-align'>
                            <c:choose>
                                <c:when test="${order.inReadingRoom}">
                                    <label>
                                        <input type="checkbox" checked disabled/>
                                    </label>
                                </c:when>
                                <c:when test="${!order.inReadingRoom}">
                                    <label>
                                        <input type="checkbox" disabled/>
                                    </label>
                                </c:when>
                            </c:choose>
                        </td>
                        <td class='center-align'>
                            <ctg:show-of-null value="${order.takingDate}" replace="-"/>
                        </td>
                        <td class='center-align'>
                            <ctg:show-of-null value="${order.returnDate}" replace="-"/>
                        </td>
                        <td><c:out value="${order.book.title}"/></td>
                        <td><c:out value="${order.reader.login}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${order.takingDate == null}">
                                    <form class="form-for-button" method="post"
                                          action="${pageContext.servletContext.contextPath}/controller?command=issueBook">
                                        <input type="hidden" value="${order.id}" name="orderId">
                                        <input type="hidden" value="${order.inReadingRoom}" name="orderInReadingRoom">
                                        <input type="hidden" value="${order.takingDate}" name="orderTakingDate">
                                        <input type="hidden" value="${order.returnDate}" name="orderReturnDate">
                                        <input type="hidden" value="${order.book.id}" name="orderBookId">
                                        <input type="hidden" value="${order.reader.id}" name="orderReaderId">
                                        <button type="submit" class="issueBook">
                                            <fmt:message bundle="${loc}" key="label.issue"/>
                                        </button>
                                    </form>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </article>
</main>
</body>
</html>