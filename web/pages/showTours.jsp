<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
</head>
<body>
<sec:authorize access="hasAuthority('manager')">
</sec:authorize>
    <sec:authorize access="hasAuthority('manager')">
        <a href="/addTour">Добавить тур</a>
        <br>
    </sec:authorize>
    <div class="scroll">
        <c:forEach var="tour" items="${tours}" >
            <table border="1" cellpadding="2" width="100%">
                <tr>
                    <th bgcolor="#dc143c">${tour.id}</th>
                    <th>
                        <sec:authorize access="!hasAuthority('manager')">
                            <a name="reservationButton" onclick="return confirm('Подтвердите бронирование тура')"
                               href="/reservationTour?id=${tour.id}">Забронировать</a>
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('manager')">
                            <a href="/listOfReservedTourUsers?id=${tour.id}">Список туристов</a>
                            <br>
                            <a href="/updateTour?id=${tour.id}">Изменить тур</a>
                            <br>
                            <a onclick="return confirm('Подтвердите удаление тура')"
                               href="/deleteTour?id=${tour.id}">Удалить тур</a>
                        </sec:authorize>
                    </th>
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
                <sec:authorize access="hasAuthority('manager')">
                    <tr>
                        <th>Всего забронировано</th>
                        <th>${tour.currentCount}</th>
                    </tr>
                    <tr>
                        <th>Всего мест</th>
                        <th>${tour.maxCount}</th>
                    </tr>
                </sec:authorize>
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
