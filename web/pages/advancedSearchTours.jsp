<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Список туров</title>
     <link href="<c:url value="/resources/css/tours.css" />" rel="stylesheet"/>
     <script type="text/javascript" src="<c:url value="/resources/script/searchParamsSaveLoad.js" />"></script>
</head>
<body onload="loadData()">

  <form:form method="post" action="/tours/advancedSearch" modelAttribute="searchTourParams">

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

  <a href="/tours/quickSearch">Quick Search</a>
  <br/>
  <a href="/logout">Logout</a>

  <div>
        <% String includedPageResult = (String) session.getAttribute("includedPageResult"); %>
        <% if (includedPageResult != null && !includedPageResult.isEmpty()) { %>
            <jsp:include page="<%= includedPageResult %>" />
        <% } %>
  </div>

</body>
</html>
