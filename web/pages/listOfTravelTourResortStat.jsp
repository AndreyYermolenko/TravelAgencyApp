<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
</head>
<body>
<div class="scroll">
    <table border="1" cellpadding="2" width="100%">
        <tr>
            <th>Название курорта</th>
            <th>Название тура</th>
            <th>Название перевозчика</th>
            <th>Цена</th>
        </tr>
        <c:forEach var="data" items="${list}" >
            <tr>
                <th>${data.destinationResort}</th>
                <c:choose>
                    <c:when test = "${data.destinationTravelTour == null}">
                        <th>Доступных туров нет</th>
                    </c:when>
                    <c:otherwise>
                        <th>${data.destinationTravelTour}</th>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test = "${data.destinationCarrier == null}">
                        <th>–</th>
                    </c:when>
                    <c:otherwise>
                        <th>${data.destinationCarrier}</th>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test = "${data.cost == 0.0}">
                        <th>–</th>
                    </c:when>
                    <c:otherwise>
                        <th>${data.cost}</th>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
