<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
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
    <div id="left-panel-center">
        <div id="menu">
            <jsp:include page="../fragments/admin-menu.jsp"/>
        </div>
    </div>
    <div id="right-panel-center">
        <div>
            <h3><i>Librarians</i></h3><br>
            <b><%= session.getAttribute("employee") %>
            </b>
            <i>2: ${sessionScope.employee}</i>
        </div>
    </div>
</div>
</body>
</html>

<%--
Мы передаем либо читателя либо админа или библиотекаря.
Где решать какую страницу открывать: в jsp или в классе *Command.
--%>
