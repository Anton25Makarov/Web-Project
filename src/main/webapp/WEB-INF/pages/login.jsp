<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%--<jsp:include page="/WEB-INF/fragments/header-label.jsp"/>--%>
<head>
    <fmt:setLocale value="ru_RU" scope="session"/>
    <%--<fmt:setBundle basename="resource."/>--%>
    <fmt:setBundle basename="pagecontent" var="rb"/>
    <title>Authorisation</title>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
</head>
<body>
<div id="head">
    Привет, Мария!
    <div>
        <fmt:message key="title" bundle="${rb}"/>
        <fmt:message key="title"/>
    </div>

    <header>
        <jsp:include page="../fragments/header-label.jsp"/>
    </header>
    <nav>
        <ul>
            <jsp:include page="../fragments/nav-language.jsp"/>
        </ul>
    </nav>
</div>
<main>
    <article>
        <div class="user">

            <header class="sign_up_header">
                <img src="${pageContext.servletContext.contextPath}/resource/images/book2.png"
                     height="50" width="50" alt="Sign up (book)"/>

                <h1 class="sign_up_title">Sign in</h1>
            </header>

            <form class="sign_up_form" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=login">
                <div class="sign_up_form_group">
                    <input type="text" name="login" placeholder="Login" required class="sign_up_input"/>
                </div>

                <div class="sign_up_form_group">
                    <input type="password" name="password" placeholder="Password" required class="sign_up_input"/>
                </div>

                <button class="sign_up_btn" type="submit">Submit</button>
            </form>

            <div class="errorInfo">
                <c:out value="${requestScope.errorLogin}"/>
            </div>
        </div>

    </article>
</main>
</body>
</html>

<%--pattern="[a-zA-Z\d]{6,20}"
                       title="Login should contain only latin characters and numbers.
                       Length between 6 and 20 chaacters"--%>
<%-- pattern=".{6,20}"
                       title="Password should contain between 6 and 20 characters"--%>