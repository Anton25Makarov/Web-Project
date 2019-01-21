<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>

<html>
<head>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/inputs.css'/>
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
                <jsp:param name="page" value="readerFindBook"/>
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
            <p class="infos">${sessionScope.bookOrderedInfo}</p>
            <c:remove var="bookOrderedInfo" scope="session"/>
        </div>
    </aside>

    <article>
        <div class="vertical-direction">


            <div class="find-line">
                <form method="post"
                      action="${pageContext.servletContext.contextPath}/controller?command=readerSelectBook">
                    <label class="inp">
                        <input type="text" placeholder="&nbsp;" name="authorName">
                        <span class="label">
                             <fmt:message bundle="${loc}" key="label.author.name"/>
                        </span>
                        <span class="border"></span>
                    </label>
                    <label class="inp">
                        <input type="text" placeholder="&nbsp;" name="authorSurname">
                        <span class="label">
                            <fmt:message bundle="${loc}" key="label.author.surname"/>
                        </span>
                        <span class="border"></span>
                    </label>
                    <label class="inp">
                        <input type="text" placeholder="&nbsp;" name="BookTitle">
                        <span class="label">
                            <fmt:message bundle="${loc}" key="label.book.title"/>
                        </span>
                        <span class="border"></span>
                    </label>
                    <label class="inp">
                        <input type="text" placeholder="&nbsp;" name="bookGenre">
                        <span class="label">
                            <fmt:message bundle="${loc}" key="label.book.genre"/>
                        </span>
                        <span class="border"></span>
                    </label>
                    <button type="submit">
                        <fmt:message bundle="${loc}" key="label.find"/>
                    </button>
                </form>
            </div>
            <div id="main-books">

                <ul id="holder">
                    <hr/>
                    <c:if test="${empty requestScope.books}">
                        <p>
                            <fmt:message bundle="${loc}" key="label.noMatchesFound"/>
                        </p>
                    </c:if>
                    <c:forEach var="book" items="${requestScope.books}">
                        <li>

                            <div class="list">
                                <div class="list-left-part">
                                    <p class="list-title">
                                        <c:out value="${book.title}"/>
                                    </p>
                                    <p class="list-author-year">
                                        <c:out value="${book.author.name}"/>
                                        <c:out value="${book.author.surname}"/>,
                                        <c:out value="${book.year}"/>
                                    </p>
                                    <p class="list-genre">
                                        <c:out value="${book.genre.genre}"/>
                                    </p>
                                </div>
                                <div class="list-center-part">
                                    <fmt:message bundle="${loc}" key="label.inStock"/>:
                                    <br/>
                                    <c:out value="${book.count}"/>
                                </div>
                                <div class="list-right-part">
                                    <form method="post"
                                          action="${pageContext.request.contextPath}/controller?command=readerOrderBook">
                                        <input type="hidden" value="${book.id}" name="bookId"/>
                                        <input type="hidden" value="${book.title}" name="bookTitle"/>
                                        <input type="hidden" value="${book.author.id}" name="authorId"/>
                                        <input type="hidden" value="${book.genre.id}" name="genreId"/>
                                        <input type="hidden" value="${book.year}" name="bookYear"/>
                                        <input type="hidden" value="${book.count}" name="bookCount"/>
                                        <button type="submit">
                                            <fmt:message bundle="${loc}" key="label.order"/>
                                        </button>
                                        <label class="m-top">
                                            <fmt:message bundle="${loc}" key="label.inReadingRoom"/>
                                            <input type="checkbox" name="inReadingRoom"/>
                                        </label>
                                    </form>
                                </div>
                            </div>
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