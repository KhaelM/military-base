<%@ page import="model.Mission" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Choix mission</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <h1>Liste des missions</h1>
    <% Mission[] missions = (Mission[]) request.getAttribute("missions"); %>
    <% for(int i = 0; i < missions.length; i++) { %>
        <div style="border:1px;border-style:solid;padding: 10px;margin-bottom: 5px">
            <h2><%= missions[i].getObjectif() %></h2>
            <a href="detailsmission?id_mission=<%= missions[i].getId() %>">Choisir mission</a>
        </div>
    <% } %>    
</body>
</html>