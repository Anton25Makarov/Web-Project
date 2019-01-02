<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" scope="session"/>
<fmt:setBundle basename="locale" var="loc"/>
<html>
<head>
    <title>
        <fmt:message bundle="${loc}" key="tab.login.title"/>
    </title>
    <link type="text/css" rel="stylesheet" href='${pageContext.servletContext.contextPath}/resource/style/style.css'/>
</head>
<body>
<div id="head">
    <%--   <div>
           <fmt:message key="title" bundle="${loc}"/>
           <fmt:message key="title"/>
       </div>--%>

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

                <h1 class="sign_up_title">
                    <fmt:message bundle="${loc}" key="label.signIn"/>
                </h1>
            </header>

            <form class="sign_up_form" method="post"
                  action="${pageContext.servletContext.contextPath}/controller?command=login">
                <div class="sign_up_form_group">
                    <fmt:message bundle="${loc}" key="input.placeholder.login" var="login"/>
                    <input type="text" name="login" placeholder="${login}" required class="sign_up_input"/>
                </div>

                <div class="sign_up_form_group">
                    <fmt:message bundle="${loc}" key="input.placeholder.password" var="password"/>
                    <input type="password" name="password" placeholder="${password}" required class="sign_up_input"/>
                </div>

                <button class="sign_up_btn" type="submit">
                    <fmt:message bundle="${loc}" key="button.submit"/>
                </button>
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