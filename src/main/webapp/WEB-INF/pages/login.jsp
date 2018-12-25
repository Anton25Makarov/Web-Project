<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<%--<jsp:include page="/WEB-INF/fragments/header-label.jsp"/>--%>
<head>
    <title>Authorisation</title>
    <style>
        <%@ include file="../style/style.css" %>
    </style>
</head>
<body>
<div id="head">
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