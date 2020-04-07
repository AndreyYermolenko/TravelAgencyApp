<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>

      <style>
          #destination {
              width: 300px;
          }
          #startTour {
              width: 150px;
          }
          #finishTour {
              width: 150px;
          }
          #minCost {
              width: 150px;
          }
          #maxCost {
              width: 150px;
          }
      </style>

      <script type="text/javascript">
          function saveData() {
              let destination_value, startTour_value, finishTour_value, minCost_value, maxCost_value;
              destination_value = document.getElementById("destination").value;
              startTour_value = document.getElementById("startTour").value;
              finishTour_value = document.getElementById("finishTour").value;
              minCost_value = document.getElementById("minCost").value;
              maxCost_value = document.getElementById("maxCost").value;

              sessionStorage.setItem("destination", destination_value);
              sessionStorage.setItem("startTour", startTour_value);
              sessionStorage.setItem("finishTour", finishTour_value);
              sessionStorage.setItem("minCost", minCost_value);
              sessionStorage.setItem("maxCost", maxCost_value);
          }
      </script>

      <script type="text/javascript">
          function loadData() {
              document.getElementById("destination").value = sessionStorage.getItem("destination");
              document.getElementById("startTour").value = sessionStorage.getItem("startTour");
              document.getElementById("finishTour").value = sessionStorage.getItem("finishTour");
              document.getElementById("minCost").value = sessionStorage.getItem("minCost");
              document.getElementById("maxCost").value = sessionStorage.getItem("maxCost");
          }
      </script>

  </head>
  <body onload="loadData()">

    <form:form method="post" action="/allTours" modelAttribute="searchTourParams">

        <input name="destination" id="destination" type="text" placeholder="Название тура"/>
        <input type="submit" value="Search" onclick="saveData()"/>
        <br/>

        <button onclick="view()">Расширенный поиск</button>
        <br/>

        <input name="startTour" id="startTour" type="date" placeholder="Дата начала" />
        <input name="finishTour" id="finishTour" type="date" placeholder="Дата окончания" />
        <br/>

        <input name="minCost" id="minCost" type="number" placeholder="Минимальная цена" />
        <input name="maxCost" id="maxCost" type="number" placeholder="Максимальная цена" />

    </form:form>

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