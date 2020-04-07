<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Введите число от 1 до 99:</p>
    <input id="number" type="text" value="10"/>
    <button type="button" onclick="myFunction()">Проверить</button>
    <p id="result"></p>

    <script>
        function myFunction() {
            var number, result;
            number = document.getElementById("number").value;
            if (isNaN(number) || number < 1 || number > 99) {
                result = "Число неверно";
            } else {
                result = "Число верно";
            }
            document.getElementById("result").innerHTML = result;
        }
    </script>
</body>
</html>
