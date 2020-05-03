<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>

<form:form method="post" action="/login">

    <input name="email" id="email" type="text" placeholder="Email"/>
    <br/>

    <input name="password" id="password" type="text" placeholder="Пароль" autocomplete="off" />
    <br/>

    <c:if test = "${error}">
        <p style="color: #cd0a0a">Invalid Email or Password<p>
    </c:if>

    <input type="submit" value="Войти"/>
    <br/>

</form:form>

    <a href="/sign_up">Регистрация</a>

</body>
</html>
