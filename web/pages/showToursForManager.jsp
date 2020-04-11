<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
</head>
<body>
    <input type="button" class="button" value="Добавить тур" onclick="location.href='addTour'" />
    <br>
    <div class="scroll">
        <c:forEach var="tour" items="${tours}" >
            <table border="1" cellpadding="2" width="100%">
                <tr>
                    <th bgcolor="#dc143c">${tour.id}</th>
                    <th>
                        <a href="/updateTour?id=${tour.id}">Изменить тур</a>
                        <br>
                        <a onclick="return confirm('Подтвердите удаление тура')"
                           href="/deleteTour?id=${tour.id}">Удалить тур</a>
                    </th>
                </tr>
                <tr>
                    <th>Название тура</th>
                    <th>${tour.destination}</th>
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
                    <th>Забронировано</th>
                    <th>${tour.currentCount}</th>
                </tr>
                <tr>
                    <th>Всего мест</th>
                    <th>${tour.maxCount}</th>
                </tr>
                <tr>
                    <th>Цена</th>
                    <th>${tour.cost}</th>
                </tr>
                <tr>
                    <th>Описание</th>
                    <th>${tour.description}</th>
                </tr>
            </table>
            <br>
        </c:forEach>
    </div>

</body>
</html>
