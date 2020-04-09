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
                <th><a href="/reservationTour?id=${tour.id}">Забронировать тур</a></th>
            </tr>
            <tr>
                <th>Название тура</th>
                <th>${tour.destination}</th>
            </tr>
            <tr>
                <th>Цена</th>
                <th>${tour.cost}</th>
            </tr>
            <tr>
                <th>Дата начала</th>
                <th>${tour.beginDate}</th>

            </tr>
            <tr>
                <th>Дата окончания</th>
                <th>${tour.endDate}</th>
            </tr>
            <tr>
                <th>Описание</th>
                <th>${tour.description}</th>
            </tr>
        </table>
        <br>
    </c:forEach>
</body>
</html>
