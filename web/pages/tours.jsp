<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Список туров</title>
      <link href="../css/tours.css" rel="stylesheet" type="text/css">

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
        <select name="sortedBy" id="sortedBy">
            <option disabled>Сортировка</option>
            <option value="destination">Название</option>
            <option value="begin_date">Дата</option>
            <option value="cost">Цена</option>
        </select>

        <label><input type="checkbox" name="desc" id="desc">
            <span>Обратная сортировка</span></label>
    </form:form>

    <jsp:include page="${includedPage}" />

    </body>
</html>



<%--    <p id="test"></p>--%>
<%--  <script>--%>
<%--      document.getElementById("test").innerHTML = sessionStorage.getItem("destination");--%>
<%--  </script>--%>
<%--        <script>--%>
<%--            document.getElementById("test").innerHTML = document.getElementById("destination").value;--%>
<%--        </script>--%>

<%--      <form:label path="destination" >Destination</form:label>--%>
<%--      <form:input path="destination" size="50"/>--%>

<%--      <form:label path="startTour" >Start tour</form:label>--%>
<%--      <form:input path="startTour" />--%>

<%--      <form:label path="finishTour" >Finish tour</form:label>--%>
<%--      <form:input path="finishTour" />--%>

<%--      <script>--%>
<%--          function showFields() {--%>

<%--              let code = "<input name=\"startTour\" id=\"startTour\" type=\"date\" placeholder=\"Дата начала\" />\n" +--%>
<%--                  "        <input name=\"finishTour\" id=\"finishTour\" type=\"date\" placeholder=\"Дата окончания\" />\n" +--%>
<%--                  "        <br/>\n" +--%>
<%--                  "\n" +--%>
<%--                  "        <input name=\"minCost\" id=\"minCost\" type=\"number\" placeholder=\"Минимальная цена\" />\n" +--%>
<%--                  "        <input name=\"maxCost\" id=\"maxCost\" type=\"number\" placeholder=\"Максимальная цена\" />"--%>
<%--              document.getElementById("fields").innerHTML = code;--%>
<%--          }--%>
<%--      </script>--%>