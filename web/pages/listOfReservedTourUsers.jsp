<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ListOfReservedTourUsers</title>
    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
</head>
<body>
    <h2>Список пользователей зарезервировавших тур:</h2>
    <div class="scroll">
        <c:forEach var="user" items="${users}" >
            <table border="1" cellpadding="2" width="100%">
                <tr>
                    <th>ID</th>
                    <th bgcolor="#dc143c" colspan="1">${user.id}</th>
                </tr>
                <tr>
                    <th width="100px">Email</th>
                    <td>${user.email}</td>
                </tr>
                <tr>
                    <th>FirstName</th>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <th>LastName</th>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <th>Manager Id</th>
                    <td>${user.managerId}</td>
                </tr>
            </table>
        </c:forEach>
    </div>
</body>
</html>
