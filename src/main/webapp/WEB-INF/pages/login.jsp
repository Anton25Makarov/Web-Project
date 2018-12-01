<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<%--<jsp:include page="/WEB-INF/fragments/header.jsp"/>--%>
<body>
<jsp:include page="../fragments/header.jsp"/>
<form action="${pageContext.servletContext.contextPath}/controller?command=login" method="post">
    <input type="text" name="Login" placeholder="Login"/><br>
    <input type="text" name="password" placeholder="Password"/><br>
    <input type="submit" value="submit"/>
</form>
</body>
</html>