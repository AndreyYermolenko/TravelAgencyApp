<%@ page import="com.yermolenko.model.TravelCarrier" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление тура</title>
</head>
<body>
    <form:form method="post" action="/addTour" modelAttribute="newTour">

        <input name="destination" id="destination" type="text" placeholder="Название тура"/>
        <br/>

        <input name="beginDate" id="beginDate" type="date" placeholder="Дата начала" />
        <br/>

        <input name="endDate" id="endDate" type="date" placeholder="Дата окончания" />
        <br/>

        <input name="cost" id="cost" type="number" placeholder="Цена" />
        <br/>

        <input name="maxCount" id="maxCount" type="number" placeholder="Макс. кол-во туристов" />
        <br/>

        <div>
            <select name="travelCarrierId" id="travelCarrierId" >
                <option disabled>Перевозчик</option>
                <c:forEach var="carrier" items="${travelCarriers}" >
                    <option value="${carrier.id}">${carrier.destination}</option>
                </c:forEach>
            </select>
        </div>

        <div>
            <select name="resortId" id="resortId" >
                <option disabled>Курорт</option>
                <c:forEach var="resort" items="${resorts}" >
                    <option value="${resort.id}">${resort.destination}</option>
                </c:forEach>
            </select>
        </div>

        <input name="description" id="description" type="text" placeholder="Описание тура"/>
        <br/>

        <input type="submit" value="Добавить тур"/>

    </form:form>
</body>
</html>
