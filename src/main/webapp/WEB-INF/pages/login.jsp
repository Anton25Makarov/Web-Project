<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>
<html>
<head>
    <title>
        <fmt:message bundle="${loc}" key="label.logIn"/>
    </title>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/resource/js/showInfo.js"></script>
</head>
<body>

<div id="head">
    <header>
        <jsp:include page="../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../fragments/nav-language.jsp">
                <jsp:param name="page" value="login"/>
            </jsp:include>
        </ul>
    </nav>
</div>
<main>
    <article>
        <div class="server-answer">
            <p class="infos">${sessionScope.parametersInfo}</p>
            <c:remove var="parametersInfo" scope="session"/>
        </div>
        <div class="user">

            <header class="sign_up_header">
                <img src="${pageContext.servletContext.contextPath}/resource/images/book.png"
                     height="50" width="50" alt="Sign up (book)"/>

                <h1 class="sign_up_title">
                    <fmt:message bundle="${loc}" key="label.signIn"/>
                </h1>
            </header>

            <form class="sign_up_form" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=login">
                <div class="sign_up_form_group">
                    <fmt:message bundle="${loc}" key="label.login" var="login"/>
                    <input type="text" name="login" placeholder="${login}" required class="sign_up_input"/>
                </div>

                <div class="sign_up_form_group">
                    <fmt:message bundle="${loc}" key="label.password" var="password"/>
                    <input type="password" name="password" placeholder="${password}" required class="sign_up_input"/>
                </div>

                <button class="sign_up_btn" type="submit">
                    <fmt:message bundle="${loc}" key="button.submit"/>
                </button>
            </form>

            <div class="errorInfo">
                <c:out value="${requestScope.errorAuthorisation}"/>
            </div>
        </div>

    </article>
</main>
</body>
</html>