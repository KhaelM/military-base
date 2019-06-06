<%@ page import="model.Mission,model.Soldat,model.Poste,model.XpMissionParPoste,model.XpSoldatParPoste" %>
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
        <% XpSoldatParPoste[] xpsSoldats =  (XpSoldatParPoste[]) request.getAttribute("xpsSoldats"); %>
        <div><strong>Mission: </strong><%= mission.getObjectif() %></div>
        <h2>Postes</h2>
        <% for(int i = 0; i < postes.length; i++) { %>
            <div>
                <h3><%= postes[i].getNom() %></h3>
                <div>XP Requis: <%= xpMissionParPostes[i].getXpMin() %></div>
                <div>Occupant: <%= soldats[i] != null ? soldats[i].getNomUtilisateur() : "Aucun" %></div>
                <% if(soldats[i] != null && session.getAttribute("categorie") != null && ((Integer)session.getAttribute("categorie")).intValue() == 1) { %>
                    <div>Xp Soldat: <%= xpsSoldats[i].getXp() %></div>
                <% } %>
            </div>
        <% } %>
        <hr>
        <% if(session.getAttribute("categorie") != null) { %>
            <% if((int)session.getAttribute("categorie") == 0) { %>
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
            <% } else { %>
                <form action="miseajourmission" method="POST">
                    <input name="id_mission" type="hidden" value='<%= request.getParameter("id_mission") %>'>
                    <button name="action" value="1" type="submit">Démarrer</button>
                    <button name="action" value="2" type="submit">Réussir</button>
                    <button name="action" value="3" type="submit">Échouer</button>
                </form>
            <% } %>
        <% } %>
    <% } %>
</body>
</html>