<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<%--<jsp:include page="/WEB-INF/fragments/header.jsp"/>--%>
<head>
    <%--<link rel="stylesheet" href="../style/style.css">--%>
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
    <%--<div id="left-panel-center">
        <div id="menu">
            <jsp:include page="../fragments/admin-menu.jsp"/>
        </div>
    </div>--%>
    <div id="right-panel-center">
        <div>
            <h3><i>Authorisation</i></h3><br>
            <form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
                <input type="text" name="login" placeholder="Login"/><br>
                <input type="text" name="password" placeholder="Password"/><br>
                <input type="submit" value="submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>