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
            src="${pageContext.servletContext.contextPath}/resource/js/list-div.js"></script>
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
        <div class="vertical-direction">


            <div class="find-line">
                <form method="post"
                      action="${pageContext.servletContext.contextPath}/controller?command=readerSelectBook">
                    <input type="text" placeholder="Author name" name="authorName"/>
                    <input type="text" placeholder="Author surname" name="authorSurname"/>
                    <input type="text" placeholder="Book title" name="BookTitle"/>
                    <input type="text" placeholder="Book genre" name="bookGenre"/>
                    <button type="submit">Find</button>
                </form>
            </div>
            <div id="main-books">

                <ul id="holder">
                    <hr/>
                    <c:forEach var="book" items="${requestScope.books}">
                        <li>
                            <c:out value="${book.title}"/>
                            <c:out value="${book.author.name}"/>
                            <c:out value="${book.author.surname}"/>
                            <c:out value="${book.genre.genre}"/>
                            <c:out value="${book.year}"/>
                            <c:out value="${book.count}"/>

                            <form method="post"
                                  action="${pageContext.request.contextPath}/controller?command=readerOrderBook">
                                <input type="hidden" value="${book.id}" name="bookId"/>
                                <input type="hidden" value="${book.title}" name="bookTitle"/>
                                <input type="hidden" value="${book.author.id}" name="authorId"/>
                                <input type="hidden" value="${book.genre.id}" name="genreId"/>
                                <input type="hidden" value="${book.year}" name="bookYear"/>
                                <input type="hidden" value="${book.count}" name="bookCount"/>
                                <input type="checkbox" name="inReadingRoom"/>
                                <button type="submit">Order</button>
                            </form>
                            <hr/>
                        </li>
                    </c:forEach>
                </ul>

            </div>
        </div>
    </article>
</main>
</body>
</html>