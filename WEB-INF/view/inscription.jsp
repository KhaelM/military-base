<%@ page contentType="text/html; charset=UTF-8" %>
<div class="signContainer" >
    <div id="blackBackground">
        <form action="inscription" method="POST">
            <div class="signTitle">Rejoignez l'armée</div>
            <hr>
            <div>
                <input type="text" name="nom_utilisateur" id="nom_utilisateur" placeholder="Nom utilisateur"
                    value='<%= request.getAttribute("nom_utilisateur") %>' autocomplete="username" />
            </div>
            <div>
                <input placeholder="Mot de passe" type="password" name="mot_de_passe" id="mot_de_passe" autocomplete="current-password" />
            </div>
            <div>
                <input placeholder="Retaper mot de passe" type="password" name="confirmation_mot_de_passe" id="confirmation_mot_de_passe" autocomplete="current-password" />
            </div>
            <button type="submit">Inscription</button>
        </form>
        <hr>
        <form action="connexion" method="get">
            <button type="submit">Connexion</button>
        </form>
    </div>
</div>