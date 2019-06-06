<%@ page import="model.Poste,model.XpSoldatParPoste" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profil</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <% Poste[] postes = (Poste[]) request.getAttribute("postes"); %>
    <% XpSoldatParPoste[] xpSoldatParPostes = (XpSoldatParPoste[]) request.getAttribute("xpSoldatParPoste"); %>
    <h1>Profil</h1>
    <div>Nom d'utilisateur: <%= session.getAttribute("nom_utilisateur") %></div>
    <div>
        <h2>Xp par poste</h2>
        <hr>
        <% for(int i = 0; i < xpSoldatParPostes.length; i++) { %>
            <div><%= postes[i].getNom() %> : <%= xpSoldatParPostes[i].getXp() %>xp</div>
        <% } %>
    </div>
</body>
</html>