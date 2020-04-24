<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список туров</title>
    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/script/reservationTour.js" />"></script>

    <script>
        let request = new XMLHttpRequest();

        function searchInfo() {
            let name = document.destinationForm.destination.value;
            let url = "/showTours?destination=" + name;

            try {
                request.onreadystatechange = function () {
                    if (request.readyState === 4 && request.status === 200) {
                        document.getElementById("includePage").innerHTML = request.responseText;
                    }
                };

                request.open("GET", url);
                request.send();
            } catch (e) {
                alert("Unable connect to server");
            }
        }
    </script>
</head>
<body>

    <form name="destinationForm" action="/tours/quickSearch">
        <input name="destination" type= "text" placeholder="Название тура"
               autocomplete="off" onkeyup="searchInfo()"/>
        <br/>
    </form>

    <a href="/tours/advancedSearch">Advanced Search</a>
    <br/>
    <sec:authorize access="!hasAuthority('manager')">
        <a id="linkReserved" href="/tours/reserved">Список забронированных туров</a>
        <br/>
    </sec:authorize>
    <a href="/logout">Logout</a>
    <br/>

    <span id="includePage"></span>

</body>
</html>
