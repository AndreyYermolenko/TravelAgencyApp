<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reserved Tours</title>
    <script>
        function hidden(){
            document.getElementById("linkReservation").hidden = true;
        }
    </script>
</head>
<body onload="hidden()">
    <h2>Список забронированных туров:</h2>
    <jsp:include page="showTours.jsp" />
</body>
</html>
