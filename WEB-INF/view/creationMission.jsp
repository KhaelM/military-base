<%@ page import="model.Poste" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    <% if(request.getAttribute("erreur") != null) { %>
        <div style="color: red;"><%= (String) request.getAttribute("erreur") %></div>
    <% } %>
    <% if(request.getAttribute("message") != null) { %>
        <div style="color: green;"><%= (String) request.getAttribute("message") %></div>
    <% } %>
    <% Poste[] postes = (Poste[]) request.getAttribute("postes"); %>
    <% if(postes != null) {%>
    <form action="creationMission" method="POST">
        <div>
            <div>
                <label for="objectif_mission">Objectif mission</label>
            </div>
            <textarea type="text" id="objectif_mission" name="objectif_mission"></textarea>
        </div>
    <fieldset>
        <legend>Gestion des postes</legend>
        <% 
            for(int i = 0; i < postes.length; i++) {
        %>
                <div>
                    <h4><%= postes[i].getNom() %></h4>
                    <label for="xp_requis_<%= postes[i].getId() %>">Xp requis</label>
                    <input type="text" id="xp_requis_<%= postes[i].getId() %>" name="xp_requis_<%= postes[i].getId() %>">
                    <label for="xp_gain_<%= postes[i].getId() %>">Xp gain</label>
                    <input type="text" id="xp_gain_<%= postes[i].getId() %>" name="xp_gain_<%= postes[i].getId() %>">
                </div>
        <%
            }
        %>
        </fieldset>
        <button type="submit">Valider</button>
        <button type="reset">Annuler</button>
    </form>
    <% } %>
</body>
</html>