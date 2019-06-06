<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Inscription</title>
</head>
<body>
    <%@ include file="../header.jsp" %>
    <fieldset>
        <legend>Inscription</legend>
        <form action="inscription" method="POST">
            <div>
                <label for="nom_utilisateur">Nom d'utilisateur</label>
                <input type="text" name="nom_utilisateur" id="nom_utilisateur" value='<%= request.getAttribute("nom_utilisateur") %>'>
            </div>
            <div>
                <label for="mot_de_passe">Mot de passe</label>
                <input type="password" name="mot_de_passe" id="mot_de_passe">
            </div>
            <input type="submit" value="Inscription">
            <div style="color: green"><%= request.getAttribute("succes") %></div>
            <div style="color:red"><%= request.getAttribute("erreur") %></div>
        </form>
    </fieldset>
</body>
</html>