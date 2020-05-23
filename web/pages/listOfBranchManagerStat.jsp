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
            <th>Фамилия менеджера</th>
            <th>Количество заказов</th>
            <th>Название отделения</th>
        </tr>
        <c:forEach var="data" items="${list}" >
            <tr>
                <th>${data.lastNameManager}</th>
                <th>${data.countOrder}</th>
                <th>${data.destinationBranch}</th>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
