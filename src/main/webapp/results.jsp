<%--
  Created by IntelliJ IDEA.
  User: ksiprus
  Date: 18.06.25
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Результаты голосования</title>
</head>
<body>
<h2>Результаты голосования</h2>

<h3>Исполнители</h3>
<ul>
    <c:forEach var="entry" items="${artists}">
        <li>${entry.key}: ${entry.value}</li>
    </c:forEach>
</ul>
<h3>Жанры</h3>
<ul>
    <c:forEach var="entry" items="${genres}">
        <li>${entry.key}: ${entry.value}</li>
    </c:forEach>
</ul>
<h3>Комментарии</h3>
<ul>
    <c:forEach var="comment" items="${abouts}">
        <li>${comment}</li>
    </c:forEach>
</ul>
<form action="${pageContext.request.contextPath}/vote" method="get">
    <input type="submit" value="Вернуться к голосованию">
</form>
</body>
</html>
