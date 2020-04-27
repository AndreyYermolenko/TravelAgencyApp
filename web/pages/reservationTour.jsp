<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reservation tour</title>
</head>
<body>
    <% if ((boolean) session.getAttribute("isSuccess")) { %>
        Тур успешно забронирован
    <% } else { %>
        При бронировании тура произошла ошибка.
        Возможно вы уже забронировали этот тур или закончились свободные места.
    <% } %>
    <br>
    <a href="/tours/quickSearch">Quick Search</a>
    <br>
    <a href="/tours/advancedSearch">Advanced Search</a>
</body>
</html>
