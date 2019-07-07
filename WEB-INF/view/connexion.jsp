<div class="signContainer" >
    <div id="blackBackground">
        <form action="connexion" method="POST">
            <div class="signTitle">Connectez-vous</div>
            <hr>
            <div>
                <input type="text" name="nom_utilisateur" id="nom_utilisateur" placeholder="Nom utilisateur"
                    value='<%= request.getAttribute("nom_utilisateur") %>' autocomplete="username" />
            </div>
            <div>
                <input placeholder="Mot de passe" type="password" name="mot_de_passe" id="mot_de_passe" autocomplete="current-password" />
            </div>
            <button type="submit">Connexion</button>
        </form>
        <hr>
        <form action="inscription" method="get">
            <button type="submit">Inscription</button>
        </form>
    </div>
</div>