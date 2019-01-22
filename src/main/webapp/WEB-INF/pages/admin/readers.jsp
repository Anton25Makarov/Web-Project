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
                <jsp:param name="page" value="addReaders"/>
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
                    <button id="saveReaderButton">
                        <fmt:message bundle="${loc}" key="label.add.reader"/>
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
                <fmt:message bundle="${loc}" key="label.readers"/>
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
                    <th>
                        <fmt:message bundle="${loc}" key="label.telephone"/>
                    </th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="reader" items="${requestScope.readers}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td><c:out value="${reader.name}"/></td>
                        <td><c:out value="${reader.surname}"/></td>
                        <td><c:out value="${reader.login}"/></td>
                        <td><c:out value="${reader.telephoneNumber}"/></td>
                        <td>
                            <form class="form-for-button" method="post"
                                  action="${pageContext.servletContext.contextPath}/controller?command=removeReader">
                                <input type="hidden" value="${reader.id}" name="readerId">
                                <button type="submit" class="removeReaderButton">
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
                  action="${pageContext.servletContext.contextPath}/controller?command=saveReader">
                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-user-reader.png"
                         alt="Add reader"
                         class="addingImage"/>
                    <h1 style="text-align:center">
                        <fmt:message bundle="${loc}" key="label.reader.adding"/>
                    </h1>
                </div>

                <div class="container">
                    <fmt:message bundle="${loc}" key="warning.input.number" var="numberWaring"/>
                    <fmt:message bundle="${loc}" key="warning.input.text" var="textWaring"/>
                    <fmt:message bundle="${loc}" key="warning.input.telephone" var="telWarning"/>
                    <fmt:message bundle="${loc}" key="label.author.name" var="name"/>
                    <input type="text" placeholder="${name}" name="name" required title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.author.surname" var="surname"/>
                    <input type="text" placeholder="${surname}" name="surname" required title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.login" var="login"/>
                    <input type="text" placeholder="${login}" name="login" required title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.password" var="password"/>
                    <input type="password" placeholder="${password}" name="password" required title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я 0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.telephone" var="telephone"/>
                    <input type="tel" placeholder="${telephone}" name="telephoneNumber" required
                           pattern="[0-9]{3}-[0-9]{2}-[0-9]{2}" title="${telWarning}">

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