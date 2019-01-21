<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            src="${pageContext.servletContext.contextPath}/resource/js/list-div.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/showInfo.js"></script>

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
                <jsp:param name="page" value="readersBooks"/>
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
            <p class="infos">${sessionScope.bookReturnInfo}</p>
            <c:remove var="bookReturnInfo" scope="session"/>
        </div>
    </aside>

    <article>
        <div class="vertical-direction list-container">

            <div id="main-books">
                <p class="page-title">
                    <fmt:message bundle="${loc}" key="label.myBooks"/>
                </p>
                <ul id="holder">
                    <hr class="hr"/>
                    <c:forEach var="order" items="${requestScope.orders}">
                        <li>
                            <div class="list">
                                <div class="list-left-part">
                                    <p class="list-title">
                                        <c:out value="${order.book.title}"/>
                                    </p>
                                    <p class="list-author-year">
                                        <c:out value="${order.book.author.name}"/>
                                        <c:out value="${order.book.author.surname}"/>,
                                        <c:out value="${order.book.year}"/>
                                    </p>
                                    <p class="list-genre">
                                        <c:out value="${order.book.genre.genre}"/>
                                    </p>
                                </div>
                                <div class="list-center-part">
                                    <fmt:message bundle="${loc}" key="label.inReadingRoom"/>
                                    <br/>
                                    <c:choose>
                                        <c:when test="${order.inReadingRoom}">
                                            <input type="checkbox" checked disabled/>
                                        </c:when>
                                        <c:when test="${!order.inReadingRoom}">
                                            <input type="checkbox" disabled/>
                                        </c:when>
                                    </c:choose>
                                </div>
                                <div class="list-right-part">
                                    <c:choose>
                                        <c:when test="${empty order.takingDate}">
                                            <fmt:message bundle="${loc}" key="label.notIssuedYet"/>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:message bundle="${loc}" key="label.issued"/>:
                                            <c:out value="${order.takingDate}"/>
                                            <br/>
                                            <form method="post"
                                                  action="${pageContext.request.contextPath}/controller?command=readerReturnBook">
                                                <input type="hidden" value="${order.book.id}" name="bookId"/>
                                                <input type="hidden" value="${order.id}" name="orderId"/>
                                                <button type="submit" class="m-top">
                                                    <fmt:message bundle="${loc}" key="label.return.book"/>
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>

                                </div>
                            </div>
                            <hr class="hr"/>
                        </li>
                    </c:forEach>
                    <hr class="hr"/>
                </ul>
            </div>
        </div>
    </article>
</main>
</body>
</html>