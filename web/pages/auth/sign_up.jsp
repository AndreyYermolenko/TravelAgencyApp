<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>

    <script>
        function validateForm() {
            let email = document.forms["sign_up"]["email"].value;
            let password = document.forms["sign_up"]["password"].value;
            let firstName = document.forms["sign_up"]["firstName"].value;
            let lastName = document.forms["sign_up"]["lastName"].value;

            if (email.length == 0) {
                document.getElementById("emailMessage").innerHTML = "Email is required.";
                return false;
            }
            if (password.length < 4) {
                document.getElementById("passwordMessage").innerHTML = "First name should be more 4 symbols.";
                return false;
            }
            if (firstName.length < 1 || firstName.length > 10) {
                document.getElementById("firstNameMessage").innerHTML = "First name should be from 1 to 10 symbols.";
                return false;
            }
            if (lastName.length < 1 || lastName.length > 10) {
                document.getElementById("lastNameMessage").innerHTML = "Last name should be from 1 to 10 symbols.";
                return false;
            }
        }
    </script>

</head>
<body>
    <form:form name="sign_up" method="post" action="/sign_up" modelAttribute="user" onsubmit="return validateForm()">

        <div>
            <form:label path="email" >Email</form:label>
            <form:input path="email" />
            <form:errors path="email" />
            <text id="emailMessage"></text>
        </div>

        <div>
            <form:label path="password" >Пароль</form:label>
            <form:input path="password" />
            <form:errors path="password" />
            <text id="passwordMessage"></text>
        </div>

        <div>
            <form:label path="firstName" >Имя</form:label>
            <form:input path="firstName" />
            <form:errors path="firstName" />
            <text id="firstNameMessage"></text>
        </div>

        <div>
            <form:label path="lastName" >Фамилия</form:label>
            <form:input path="lastName" />
            <form:errors path="lastName" />
            <text id="lastNameMessage"></text>
        </div>

        <input type="submit" value="Регистрация"/>

    </form:form>
</body>
</html>
