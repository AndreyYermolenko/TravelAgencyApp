<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="tour" items="${tours}" >
        <table border="1" cellpadding="2" width="100%">
            <tr>
                <th bgcolor="#dc143c">${tour.id}</th>
                <th><a href="/changeTour?id=${tour.id}">Изменить тур</a></th>
                <th><a href="/deleteTour?id=${tour.id}">Удалить тур</a></th>
            </tr>
            <tr>
                <th>Название тура</th>
                <th>Дата начала</th>
                <th>Дата окончания</th>
            </tr>
            <tr>
                <th>${tour.destination}</th>
                <th>${tour.beginDate}</th>
                <th>${tour.endDate}</th>
            </tr>
            <tr>
                <th>Количество</th>
                <th>Максимально количество</th>
                <th>Цена</th>
            </tr>
            <tr>
                <th>${tour.currentCount}</th>
                <th>${tour.maxCount}</th>
                <th>${tour.cost}</th>
            </tr>
            <tr>
                <th colspan="3">Описание</th>
            </tr>
            <tr>
                <th colspan="3">${tour.description}</th>
            </tr>
        </table>
        <br>
    </c:forEach>
</body>
</html>
