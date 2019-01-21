<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>
<html>
<head>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/modal.css'/>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/table.css'/>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/jquery-3.3.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/jquery-datatable.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/modal-librarian.js"></script>
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
                <jsp:param name="page" value="addLibrarians"/>
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

                </c:when>
                <c:when test="${sessionScope.role == 'reader'}">
                    <jsp:include page="../../fragments/reader/reader-menu.jsp"/>
                </c:when>
            </c:choose>
            <div class="vertical-direction">
                <div class="button-info">
                    <button id="saveLibrarianButton" class="menu-but">
                        <fmt:message bundle="${loc}" key="label.add.librarian"/>
                    </button>
                </div>
                <span><c:out value="${param.save}"/></span>
            </div>
        </div>
        <div class="server-answer">
            <p class="infos">${sessionScope.parametersInfo}</p>
            <c:remove var="parametersInfo" scope="session"/>
            <p class="infos">${sessionScope.correctLoginInfo}</p>
            <c:remove var="correctLoginInfo" scope="session"/>
            <p class="infos">${sessionScope.saveStatusInfo}</p>
            <c:remove var="saveStatusInfo" scope="session"/>
            <p class="infos">${sessionScope.removeStatusInfo}</p>
            <c:remove var="removeStatusInfo" scope="session"/>
        </div>
    </aside>

    <article>
        <div>
            <p class="page-title">
                <fmt:message bundle="${loc}" key="label.librarians"/>
            </p>
            <table id="table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.author.name"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.author.surname"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.login"/>
                    </th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="librarian" items="${requestScope.librarians}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td><c:out value="${librarian.name}"/></td>
                        <td><c:out value="${librarian.surname}"/></td>
                        <td><c:out value="${librarian.login}"/></td>
                        <td>
                            <form class="form-for-button" method="post"
                                  action="${pageContext.servletContext.contextPath}/controller?command=removeLibrarian">
                                <input type="hidden" value="${librarian.id}" name="librarianId">
                                <button type="submit" class="removeLibrarianButton">
                                    <fmt:message bundle="${loc}" key="label.remove"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="modal-wrapper-librarian-insert" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=saveLibrarian">
                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png"
                         alt="Add librarian"
                         class="addingImage"/>
                    <h1 style="text-align:center">
                        <fmt:message bundle="${loc}" key="label.librarian.adding"/>
                    </h1>
                </div>

                <div class="container">
                    <fmt:message bundle="${loc}" key="warning.input.number" var="numberWaring"/>
                    <fmt:message bundle="${loc}" key="warning.input.text" var="textWaring"/>
                    <fmt:message bundle="${loc}" key="label.author.name" var="name"/>
                    <input type="text" placeholder="${name}" name="name" required  title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.author.surname" var="surname"/>
                    <input type="text" placeholder="${surname}" name="surname" required  title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.login" var="login"/>
                    <input type="text" placeholder="${login}" name="login" required  title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.password" var="password"/>
                    <input type="password" placeholder="${password}" name="password" required  title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">

                    <button type="submit">
                        <fmt:message bundle="${loc}" key="label.save"/>
                    </button>
                </div>
            </form>
        </div>
    </article>
</main>
</body>
</html>