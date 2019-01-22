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
            src="${pageContext.servletContext.contextPath}/resource/js/modal-book.js"></script>
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
                <jsp:param name="page" value="addBooks"/>
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
                    <button id="saveBookButton">
                        <fmt:message bundle="${loc}" key="label.add.book"/>
                    </button>
                </div>
                <div class="button-info">
                    <button id="addAuthorButton">
                        <fmt:message bundle="${loc}" key="label.add.author"/>
                    </button>
                </div>
                <div class="button-info">
                    <button id="addGenreButton">
                        <fmt:message bundle="${loc}" key="label.add.genre"/>
                    </button>
                </div>
                <span> <c:out value="${param.save}"/></span>
            </div>
        </div>
        <div class="server-answer">
            <p class="infos">${sessionScope.parametersInfo}</p>
            <c:remove var="parametersInfo" scope="session"/>
            <p class="infos">${sessionScope.saveStatusInfo}</p>
            <c:remove var="saveStatusInfo" scope="session"/>
            <p class="infos">${sessionScope.removeStatusInfo}</p>
            <c:remove var="removeStatusInfo" scope="session"/>
        </div>
    </aside>

    <article>
        <div>
            <p class="page-title">
                <fmt:message bundle="${loc}" key="label.books"/>
            </p>
            <table id="table">
                <thead>
                <tr>
                    <th>№</th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.book.title"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.book.author"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.book.genre"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.year"/>
                    </th>
                    <th>
                        <fmt:message bundle="${loc}" key="label.amount"/>
                    </th>
                    <th></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${requestScope.books}" varStatus="status">
                    <tr>
                        <td><c:out value="${status.count}"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td>
                            <c:out value="${book.author.name}"/>
                            <c:out value="${book.author.surname}"/>
                        </td>
                        <td><c:out value="${book.genre.genre}"/></td>
                        <td><c:out value="${book.year}"/></td>
                        <td><c:out value="${book.count}"/></td>
                        <td>
                            <button type="submit" class="saveEditBookButton" value="${book.id}">
                                <fmt:message bundle="${loc}" key="label.edit"/>
                            </button>
                        </td>
                        <td>
                            <form class="form-for-button" method="post"
                                  action="${pageContext.servletContext.contextPath}/controller?command=removeBook">
                                <input type="hidden" value="${book.id}" name="bookId">
                                <button type="submit" class="saveRemoveBookButton">
                                    <fmt:message bundle="${loc}" key="label.remove"/>
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div id="modal-wrapper-book-insert" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=saveBook">
                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png"
                         alt="Add book"
                         class="addingImage"/>
                    <h1 style="text-align:center">
                        <fmt:message bundle="${loc}" key="label.book.adding"/>
                    </h1>
                </div>

                <div class="container">
                    <fmt:message bundle="${loc}" key="label.book.title" var="title"/>
                    <fmt:message bundle="${loc}" key="warning.input.number" var="numberWaring"/>
                    <fmt:message bundle="${loc}" key="warning.input.text" var="textWaring"/>
                    <input type="text" placeholder="${title}" name="bookTitle" required
                           title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9 ]{2,15}"/>
                    <label>
                        <select name="selectedGenreId" required>
                            <c:forEach items="${requestScope.genres}" var="genre">
                                <option value="${genre.id}"><c:out value="${genre.genre}"/></option>
                            </c:forEach>
                        </select>
                    </label>
                    <label>
                        <select name="selectedAuthorId" title="authors" required>
                            <c:forEach items="${requestScope.authors}" var="author">
                                <option value="${author.id}">
                                    <c:out value="${author.name}"/>
                                    <c:out value="${author.surname}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </label>
                    <fmt:message bundle="${loc}" key="label.amount" var="count"/>
                    <input type="number" placeholder="${count}" name="bookCount" required
                           title="${numberWaring}" min="0" max="100"
                           pattern="[0-9]{1,3}">
                    <fmt:message bundle="${loc}" key="label.year" var="year"/>
                    <input type="number" placeholder="${year}" name="bookYear" required
                           title="${numberWaring}" min="1500" max="2019"
                           pattern="[0-9]{4}">
                    <input type="hidden" name="bookId">
                    <button type="submit">
                        <fmt:message bundle="${loc}" key="label.save"/>
                    </button>
                </div>
            </form>

        </div>

        <div id="modal-wrapper-author" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=addAuthor">

                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png" alt="Add author"
                         class="addingImage"/>
                    <h1 style="text-align:center">
                        <fmt:message bundle="${loc}" key="label.author.adding"/>
                    </h1>
                </div>

                <div class="container">
                    <fmt:message bundle="${loc}" key="label.author.name" var="name"/>
                    <input type="text" placeholder="${name}" name="authorBookName" required
                           title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <fmt:message bundle="${loc}" key="label.author.surname" var="surname"/>
                    <input type="text" placeholder="${surname}" name="authorBookSurname" required
                           title="${textWaring}"
                           pattern="[a-zA-Zа-яА-Я0-9]{2,15}">
                    <button type="submit">
                        <fmt:message bundle="${loc}" key="label.save"/>
                    </button>
                </div>

            </form>

        </div>

        <div id="modal-wrapper-genre" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=addGenre">

                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png" alt="Add genre"
                         class="addingImage"/>
                    <h1 style="text-align:center">
                        <fmt:message bundle="${loc}" key="label.genre.adding"/>
                    </h1>
                </div>
                <div class="container">
                    <fmt:message bundle="${loc}" key="label.book.genre" var="genre"/>
                    <input type="text" placeholder="${genre}" name="bookGenre" required
                           title="${textWaring}" pattern="[a-zA-Zа-яА-Я 0-9]{2,15}">
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

