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
        <jsp:include page="../fragments/header-label.jsp"/>
    </div>
</div>
<div id="center-panel">
    <div id="left-panel-center">
        <div id="menu">
            <jsp:include page="../fragments/admin/admin-menu.jsp"/>
        </div>
    </div>
    <div id="right-panel-center">
        <div>
            <h3><i>Readers</i></h3><br>
        </div>
    </div>
</div>
</body>
</html>