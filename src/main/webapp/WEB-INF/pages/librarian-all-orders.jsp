<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
        <jsp:include page="../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../fragments/nav-language.jsp">
                <jsp:param name="page" value="main"/>
            </jsp:include>
            <jsp:include page="../fragments/nav-logout.jsp"/>
        </ul>
    </nav>
</div>
<main>
    <aside>
        <div class="menu">
            <c:choose>
                <c:when test="${sessionScope.role == 'employee' and sessionScope.user.admin}">
                    <jsp:include page="../fragments/admin/admin-menu.jsp"/>
                </c:when>
                <c:when test="${sessionScope.role == 'employee' and !sessionScope.user.admin}">
                    <jsp:include page="../fragments/librarian/librarian-menu.jsp"/>
                </c:when>
                <c:when test="${sessionScope.role == 'reader'}">
                    <jsp:include page="../fragments/reader/reader-menu.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </aside>

    <article>
        <div>
            <table id="table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Reading room</th>
                    <th>Taking date</th>
                    <th>Return Date</th>
                    <th>Book id</th>
                    <th>Reader id</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="order" items="${requestScope.orders}">
                    <tr>
                        <td><c:out value="${order.id}"/></td>
                        <td>
                            <c:choose>
                                <c:when test="${order.inReadingRoom}">
                                    <input type="checkbox" checked disabled/>
                                </c:when>
                                <c:when test="${!order.inReadingRoom}">
                                    <input type="checkbox" disabled/>
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${order.takingDate != null}">
                                    <c:out value="${order.takingDate}"/>
                                </c:when>
                                <c:when test="${order.takingDate == null}">
                                    -
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${order.returnDate != null}">
                                    <c:out value="${order.returnDate}"/>
                                </c:when>
                                <c:when test="${order.returnDate == null}">
                                    -
                                </c:when>
                            </c:choose>
                        </td>
                        <td><c:out value="${order.book.id}"/></td>
                        <td><c:out value="${order.reader.id}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </article>
</main>
</body>
</html>