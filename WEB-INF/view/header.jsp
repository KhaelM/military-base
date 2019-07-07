<%@ page contentType="text/html; charset=UTF-8" %>
<header>
    <nav>
        <ul>
            <li class='<%= ((String)request.getAttribute("active_menu")).equals("accueil") ? "active" : "" %>'><a href="accueil">Accueil</a></li>
            <li><a href="listemissions">Missions</a></li>
            <% if(session.getAttribute("nom_utilisateur") != null) { %>
            <li><a href="profil">Profil</a></li>
            <% } %>
            <% if(session.getAttribute("nom_utilisateur") != null && (int) session.getAttribute("categorie") == 1) { %>
                <li><a href="creationMission">Bureau</a></li>
            <% } %>
            <% if(session.getAttribute("nom_utilisateur") == null) { %>
                <li class='<%= ((String)request.getAttribute("active_menu")).equals("connexion") ? "active" : "" %>'><a href="connexion">Connexion</a></li>
                <li class='<%= ((String)request.getAttribute("active_menu")).equals("inscription") ? "active" : "" %>'><a href="inscription">Inscription</a></li>
            <% } %>
            <% if(session.getAttribute("nom_utilisateur") != null) { %>
                <li id="deconnexion"><a href="deconnexion">Deconnexion</a></li>                
            <% } %>
        </ul>
    </nav>
</header>