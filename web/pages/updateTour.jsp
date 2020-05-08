<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Редактирование тура</title>
</head>
<body>
    <form:form method="post" action="/updateTour?id=${tourCurrent.id}" modelAttribute="tourUpdate">

        <input name="destination" id="destination" type="text" value="${tourCurrent.destination}" placeholder="Название тура"/>
        <br/>

        <input name="beginDate" id="beginDate" type="date" value="${tourCurrent.beginDate}" placeholder="Дата начала" />
        <br/>

        <input name="endDate" id="endDate" type="date" value="${tourCurrent.endDate}" placeholder="Дата окончания" />
        <br/>

        <input name="cost" id="cost" type="number" value="${tourCurrent.cost}" placeholder="Цена" />
        <br/>

        <input name="maxCount" id="maxCount" type="number" value="${tourCurrent.maxCount}" placeholder="Макс. кол-во туристов" />
        <br/>

        <div>
            <select name="traverCarrierId" id="traverCarrierId" >
                <option disabled>Транспортный перевозчик</option>
                <c:forEach var="carrier" items="${travelCarriers}" >
                    <c:choose>
                        <c:when test = "${carrier.id  == tourCurrent.traverCarrier.id}">
                            <option selected value="${carrier.id}">${carrier.destination}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${carrier.id}">${carrier.destination}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div>
            <select name="resortId" id="resortId" >
                <option disabled>Отделение агенства</option>
                <c:forEach var="resort" items="${resorts}" >
                    <c:choose>
                        <c:when test = "${resort.id  == tourCurrent.resort.id}">
                            <option selected value="${resort.id}">${resort.destination}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${resort.id}">${resort.destination}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <input name="description" id="description" type="text" value="${tourCurrent.description}" placeholder="Описание тура"/>
        <br/>

        <input type="submit" value="Сохранить именения"/>

    </form:form>

</body>
</html>
