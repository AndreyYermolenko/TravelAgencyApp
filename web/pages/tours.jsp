<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>Список туров</title>
      <link href="<c:url value="/resources/css/tours.css" />" rel="stylesheet">

      <script type="text/javascript">
          function saveData() {
              let destination_value, beginDate_value, endDate_value, minCost_value, maxCost_value;
              destination_value = document.getElementById("destination").value;
              beginDate_value = document.getElementById("beginDate").value;
              endDate_value = document.getElementById("endDate").value;
              minCost_value = document.getElementById("minCost").value;
              maxCost_value = document.getElementById("maxCost").value;

              sessionStorage.setItem("destination", destination_value);
              sessionStorage.setItem("beginDate", beginDate_value);
              sessionStorage.setItem("endDate", endDate_value);
              sessionStorage.setItem("minCost", minCost_value);
              sessionStorage.setItem("maxCost", maxCost_value);

          }
      </script>

      <script type="text/javascript">
          function loadData() {
              document.getElementById("destination").value = sessionStorage.getItem("destination");
              document.getElementById("beginDate").value = sessionStorage.getItem("beginDate");
              document.getElementById("endDate").value = sessionStorage.getItem("endDate");
              document.getElementById("minCost").value = sessionStorage.getItem("minCost");
              document.getElementById("maxCost").value = sessionStorage.getItem("maxCost");

          }
      </script>

  </head>
  <body onload="loadData()">

    <form:form method="post" action="/tours" modelAttribute="searchTourParams">

        <input name="destination" id="destination" type="text" placeholder="Название тура"/>
        <input type="submit" value="Поиск" onclick="saveData()"/>
        <br/>

        <input name="beginDate" id="beginDate" type="date" placeholder="Дата начала" />
        <input name="endDate" id="endDate" type="date" placeholder="Дата окончания" />
        <br/>

        <input name="minCost" id="minCost" type="number" placeholder="Минимальная цена" />
        <input name="maxCost" id="maxCost" type="number" placeholder="Максимальная цена" />

        <br>
        <select name="sortedBy" id="sortedBy" >
            <option disabled>Сортировка</option>
            <option value="destination">Название</option>
            <option value="begin_date">Дата</option>
            <option value="cost">Цена</option>
        </select>

        <label><input type="checkbox" name="desc" id="desc">
            <span>Обратная сортировка</span></label>
    </form:form>

    <a href="/logout">Logout</a>

    <div>
        <% String includedPage = (String) session.getAttribute("includedPage"); %>
        <% if (includedPage != null && !includedPage.isEmpty()) { %>
            <jsp:include page="<%= includedPage %>" />
        <% } %>
    </div>

    </body>
</html>
