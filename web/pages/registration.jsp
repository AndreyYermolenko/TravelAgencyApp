<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>

    <form:form method="post" action="/registration" modelAttribute="user">

        <input name="email" id="email" type="text" placeholder="Email"/>
        <br/>

        <input name="password" id="password" type="text" placeholder="Пароль" />
        <br/>

        <input name="firstName" id="firstName" type="text" placeholder="Имя" />
        <br/>

        <input name="lastName" id="lastName" type="text" placeholder="Фамилия" />
        <br/>

        <input type="submit" value="Регистрация"/>

    </form:form>
</body>
</html>
