<%--<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<head>
    <title>Authorisation</title>
</head>
<body>
<form action="controller?command=login" method="post">
    <input type="text" name="Login" placeholder="Login"/><br>
    <input type="text" name="password" placeholder="Password"/><br>
    <input type="submit" value="submit"/>
</form>
</body>
</html>--%>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>

<html>
<jsp:forward page="WEB-INF/pages/login.jsp"/>
</html>