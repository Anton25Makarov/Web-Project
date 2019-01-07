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
            src="${pageContext.servletContext.contextPath}/resource/js/modal-librarian.js"></script>
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
            <table id="table">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Telephone</th>
                    <th>Remove</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="reader" items="${requestScope.readers}">
                    <tr>
                        <td><c:out value="${reader.id}"/></td>
                        <td><c:out value="${reader.name}"/></td>
                        <td><c:out value="${reader.surname}"/></td>
                        <td><c:out value="${reader.login}"/></td>
                        <td><c:out value="${reader.password}"/></td>
                        <td><c:out value="${reader.telephoneNumber}"/></td>
                        <td>
                            <form class="form-for-button" method="post"
                                  action="${pageContext.servletContext.contextPath}/controller?command=removeReader">
                                <input type="hidden" value="${reader.id}" name="readerId">
                                <button type="submit" class="removeReaderButton">Remove</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="vertical-direction">
            <div class="button-info">
                <button id="saveLibrarianButton">Add reader</button>
            </div>
            <span><c:out value="${param.save}"/></span>
        </div>

        <div id="modal-wrapper-librarian-insert" class="modal">

            <form class="modal-content animate" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=saveReader">
                <div class="imgContainer">
                    <span class="close modalCross" title="Close">&times;</span>
                    <img src="${pageContext.servletContext.contextPath}/resource/images/add-book.png"
                         alt="Add reader"
                         class="addingImage"/>
                    <h1 style="text-align:center">Adding reader</h1>
                </div>

                <div class="container">

                    <input type="text" placeholder="Name" name="name" required pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Surname" name="surname" required pattern="[a-zA-Z]{2,15}">
                    <input type="text" placeholder="Login" name="login" required pattern="[a-zA-Z]{2,15}">
                    <input type="password" placeholder="Password" name="password" required pattern="[a-zA-Z]{2,15}">
                    <input type="tel" placeholder="Telephone number" name="telephoneNumber" required
                           pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}" title="'123-456-7890'">

                    <button type="submit">Save librarian</button>
                </div>
            </form>

        </div>

    </article>
</main>
</body>
</html>