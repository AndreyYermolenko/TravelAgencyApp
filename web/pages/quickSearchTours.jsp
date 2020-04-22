<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список туров</title>
    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/script/resultQuickSearch.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/script/reservationTour.js" />"></script>

    <script>
        let request = new XMLHttpRequest();

        function searchInfo(role) {
            let name = document.destinationForm.destination.value;
            let url = "/getToursQuick?destination=" + name;
            let content;

            try {
                request.onreadystatechange = function () {
                    if (request.readyState === 4 && request.status === 200) {
                        content = JSON.parse(request.responseText);
                        if (role === 'manager') {
                            document.getElementById("includePage").innerHTML = resultForManager(content)
                        } else {
                           document.getElementById("includePage").innerHTML = resultForUser(content);
                        }
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
               autocomplete="off" onkeyup="searchInfo('${role}')"/>
        <br/>
    </form>

    <a href="/tours/advancedSearch">Advanced Search</a>
    <br/>
    <a href="/logout">Logout</a>
    <br/>

    <span id="includePage"></span>

</body>
</html>
