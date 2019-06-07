<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Résultats de postulation</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <div style="color: red;"><%= request.getAttribute("erreur") %></div>
    <div style="color: green;"><%= request.getAttribute("message") %></div>
</body>
</html>