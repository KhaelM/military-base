<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <% if(((String)request.getAttribute("erreur")).length() != 0) { %>
        <div style="color: red;"><%= (String) request.getAttribute("erreur") %></div>
    <% } %>
    <% if(((String)request.getAttribute("message")).length() != 0) { %>
        <div style="color: green;"><%= (String) request.getAttribute("message") %></div>
    <% } %>
    <fieldset>
        <legend>Creer mission</legend>
    </fieldset>
</body>
</html>