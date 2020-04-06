<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--    <form action = "/" method="post" >--%>
<%--      <input name="destination" type="text" placeholder="destination"/> <br/>--%>
<%--      <input name="cost" type="number" placeholder="cost"/> <br/>--%>
<%--      <input name="startTour" type="date" placeholder="startTour"/> <br/>--%>
<%--      <input name="endTour" type="date" placeholder="endTour"/> <br/>--%>
<%--      <input type="submit"/>--%>
<%--    </form>--%>

    <form:form method="post" action="/allTours"
               modelAttribute="searchTourParams">

      <form:label path="destination" >Destination</form:label>
      <form:input path="destination" size="50" />

      <input type="submit" value="Search" />
      <br/>

<%--      <input name="startTour" type="date" placeholder="startTour" />--%>

      <form:label path="startTour" >Start tour</form:label>
      <form:input path="startTour" />

      <form:label path="finishTour" >Finish tour</form:label>
      <form:input path="finishTour" />
      <br/>

      <form:label path="minCost" >Cost</form:label>
      <form:input path="minCost" />

      <form:label path="maxCost" >Cost</form:label>
      <form:input path="maxCost" />

    </form:form>
  </body>
</html>
