<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>

<form:form method="post" action="/login/process">

    <input name="email" id="email" type="text" placeholder="Email"/>
    <br/>

    <input name="password" id="password" type="text" placeholder="Пароль" />
    <br/>

    <input type="submit" value="Войти"/>
    <br/>

</form:form>

    <a href="sign_up">Регистрация</a>

</body>
</html>
