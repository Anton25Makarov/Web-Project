<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<%--<jsp:include page="/WEB-INF/fragments/header.jsp"/>--%>
<head>
    <%--<link rel="stylesheet" href="../style/style.css">--%>
    <title>Authorisation</title>
    <style>
        <%@ include file="../style/style.css" %>
    </style>
</head>
<body>
<div id="top-panel">
    <div id="header">
        <jsp:include page="../fragments/header.jsp"/>
    </div>
</div>
<div id="center-panel">
    <div id="right-panel-center">
        <div>
            <h3><i>Authorisation</i></h3><br>
            <form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
                <input type="text" name="login" placeholder="Login" required/><br>
                <input type="password" name="password" placeholder="Password" required/><br>
                <input type="submit" value="submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>

<%--pattern="[a-zA-Z\d]{6,20}"
                       title="Login should contain only latin characters and numbers.
                       Length between 6 and 20 chaacters"--%>
<%-- pattern=".{6,20}"
                       title="Password should contain between 6 and 20 characters"--%>