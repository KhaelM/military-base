<%@ page import="model.Mission,model.Soldat,model.Poste,model.XpMissionParPoste" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Details mission</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <% if((String) request.getAttribute("erreur") != "") { %>
        <div><%= (String) request.getAttribute("erreur") %></div>
    <% } %>
    <% Mission mission = (Mission) request.getAttribute("mission"); %>
    <% if(mission != null) { %>
        <% XpMissionParPoste[] xpMissionParPostes = (XpMissionParPoste[]) request.getAttribute("xpMissionParPostes"); %>
        <% Poste[] postes = (Poste[]) request.getAttribute("postes"); %>
        <% Soldat[] soldats = (Soldat[]) request.getAttribute("soldats"); %>
        <div><strong>Mission: </strong><%= mission.getObjectif() %></div>
        <h2>Postes</h2>
        <% for(int i = 0; i < postes.length; i++) { %>
            <div>
                <h3><%= postes[i].getNom() %></h3>
                <div>XP Requis: <%= xpMissionParPostes[i].getXpMin() %></div>
                <div>Occupant: <%= soldats[i] != null ? soldats[i].getNomUtilisateur() : "Aucun" %></div>
            </div>
        <% } %>
        <hr>
        <form action="postuler" method="POST">
            <label for="id_poste">Choisir poste</label>
            <select name="id_poste" id="id_poste">
                <% for(int i = 0; i < postes.length; i++) { %>
                    <option value="<%= postes[i].getId() %>"><%= postes[i].getNom() %></option>
                <% } %>
            </select>
            <input name="id_mission" type="hidden" value='<%= request.getParameter("id_mission") %>'>
            <input type="submit" value="Postuler">
        </form>
    <% } %>
</body>
</html>