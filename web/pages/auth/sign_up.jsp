<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
    <form:form method="post" action="/sign_up" modelAttribute="user">

        <div>
            <form:label path="email" >Email</form:label>
            <form:input path="email" />
            <form:errors path="email" />
        </div>

        <div>
            <form:label path="password" >Пароль</form:label>
            <form:input path="password" />
            <form:errors path="password" />
        </div>

        <div>
            <form:label path="firstName" >Имя</form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" />
        </div>

        <div>
            <form:label path="lastName" >Фамилия</form:label>
            <form:input path="lastName" />
            <form:errors path="lastName" />
        </div>

        <input type="submit" value="Регистрация"/>

    </form:form>
</body>
</html>
