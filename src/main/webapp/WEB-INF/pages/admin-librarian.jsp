<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            src="${pageContext.servletContext.contextPath}/resource/js/modal.js?newversion"></script>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/hideInfo.js?newversion"></script>

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
                <jsp:param name="page" value="addBook"/>
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

                </c:when>
                <c:when test="${sessionScope.role == 'reader'}">
                    <jsp:include page="../fragments/reader/reader-menu.jsp"/>
                </c:when>
            </c:choose>
        </div>
    </aside>

    <article>
        <div>
            <table id="bookTable">
                <thead>
                <tr>
                    <th>Id</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${requestScope.books}">
                    <tr>
                        <td><c:out value="${book.id}"/></td>
                        <td><c:out value="${book.title}"/></td>
                        <td>
                            <c:out value="${book.author.name}"/>
                            <c:out value="${book.author.surname}"/>
                        </td>
                        <td><c:out value="${book.genre.genre}"/></td>
                        <td><c:out value="${book.year}"/></td>
                        <td><c:out value="${book.count}"/></td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="vertical-direction">
            <div class="button-info">
                <button id="saveBookButton">Add book</button>
            </div>
            <div class="button-info">
                <button id="addAuthorButton">Add author</button>
            </div>
            <div class="button-info">
                <button id="addGenreButton">Add genre</button>
            </div>
            <span> <c:out value="${param.save}"/></span>
        </div>

        <div id="modal-wrapper-author" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=addAuthor">

                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png" alt="Add author"
                         class="addBookImage"/>
                    <h1 style="text-align:center">Adding author</h1>
                </div>

                <div class="container">
                    <input type="text" placeholder="Author name" name="authorBookName" required
                           pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Author surname" name="authorBookSurname" required
                           pattern="[a-zA-Z]{2,15}">
                    <button type="submit">Add author</button>
                </div>

            </form>

        </div>

        <div id="modal-wrapper-genre" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=addGenre">

                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png" alt="Add genre"
                         class="addBookImage"/>
                    <h1 style="text-align:center">Adding genre</h1>
                </div>
                <div class="container">
                    <input type="text" placeholder="Genre" name="bookGenre" required pattern="[a-zA-Z]{2,15}">
                    <button type="submit">Add genre</button>
                </div>

            </form>

        </div>

    </article>
</main>
</body>
</html>