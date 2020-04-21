<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список туров</title>
    <link href="<c:url value="/resources/css/scroll.css" />" rel="stylesheet">
    <script type="text/javascript" src="<c:url value="/resources/script/resultQuickSearch.js" />"></script>

    <script>
        let request1 = new XMLHttpRequest();
        let request2 = new XMLHttpRequest();

        function searchInfo() {
            let name = document.destinationForm.destination.value;
            let url1 = "/getToursQuick?destination=" + name;
            let url2 = "/getCurrentUserRoles";
            let content1;
            let content2;
            let isManager = false;

            try {
                request1.onreadystatechange = function () {
                    if ((request1.readyState === 4 && request1.status === 200)
                        && (request2.readyState === 4 && request2.status === 200)) {
                        content1 = JSON.parse(request1.responseText);
                        content2 = JSON.parse(request2.responseText);
                        for(let i in content2) {
                            if (content2[i] === 'manager') {
                                isManager = true;
                                document.getElementById("includePage").innerHTML = resultForManager(content1);
                            }
                        }

                        if (!isManager) {
                            document.getElementById("includePage").innerHTML = resultForUser(content1);
                        }
                    }
                };

                request1.open("GET", url1);
                request1.send();

                request2.open("GET", url2);
                request2.send();

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
    <a href="/logout">Logout</a>
    <br/>

    <span id="includePage"></span>

</body>
</html>
