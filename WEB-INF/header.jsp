<%@ page contentType="text/html; charset=UTF-8" %>
<header>
    <nav>
        <ul>
            <li><a href="accueil">Accueil</a></li>
            <li><a href="listemissions">Liste missions</a></li>
            <% if(session.getAttribute("nom_utilisateur") != null && (int) session.getAttribute("categorie") == 1) { %>
                <li><a href="creationmission">Creation mission</a></li>
            <% } %>
            <% if(session.getAttribute("nom_utilisateur") == null) { %>
                <li><a href="connexion">Connexion</a></li>
                <li><a href="inscription">Inscription</a></li>
            <% } %>
            <% if(session.getAttribute("nom_utilisateur") != null) { %>
            <li><a href="deconnexion">Deconnexion</a></li>
            <li><a href="profil"><%= session.getAttribute("nom_utilisateur") %></a></li>
            <% } %>
        </ul>
    </nav>
</header>