<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <style>
        <%@ include file="../style/style.css" %>
        <%@ include file="../style/modal.css" %>
    </style>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/jquery-3.3.1.js"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/modal.js"></script>

    <title>Menu</title>
</head>
<body>
<div id="head">
    <header>
        <jsp:include page="../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../fragments/nav-language.jsp"/>
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

                </c:when>
                <c:when test="${sessionScope.role == 'reader'}">
                    <jsp:include page="../fragments/reader/reader-menu.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </aside>

    <article>
        <div>
            <table>
                <tbody>
                <tr>
                    <th>№</th>
                    <th>Book name</th>
                    <th>Book author</th>
                    <th>Genre</th>
                    <th>Year</th>
                    <th>Count</th>
                </tr>
                <c:forEach var="book" items="${requestScope.books}">
                    <tr>
                        <td><c:out value="${book.id}"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td>
                            <c:out value="${book.author.name}"/>
                            <c:out value="${book.author.surname}"/>
                        </td>
                        <td><c:out value="${book.genre}"/></td>
                        <td><c:out value="${book.year}"/></td>
                        <td><c:out value="${book.count}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <button id="addBookButton">
            Add book
        </button>
        <div id="modal-wrapper" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=addBook">

                <div class="imgcontainer">
                    <span id="modalCross" class="close" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png" alt="Add book"
                         class="addBookImage"/>
                    <h1 style="text-align:center">Adding book</h1>
                </div>

                <div class="container">
                    <input type="text" placeholder="Title" name="bookTitle" required pattern="[a-zA-Z\\d]{2,15}">
                    <input type="text" placeholder="Genre" name="bookGenre" required pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Author name" name="bookAuthorName" required
                           pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Author surname" name="bookAuthorSurname" required
                           pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Count" name="bookCount" required pattern="\d+">
                    <button type="submit">Add book</button>
                </div>

            </form>

        </div>


    </article>
</main>
</body>
</html>