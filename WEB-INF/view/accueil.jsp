<%@ page contentType="text/html; charset=UTF-8" %>
<div class="content">
  <div id="welcomeLogin"><%= (String) request.getAttribute("message_accueil") %></div>
  <% if(session.getAttribute("id_utilisateur") != null) { %>
      <div id="welcomePseudo"><%= (String) session.getAttribute("nom_utilisateur") %></div>
  <% } %>
  <hr id="separatorLogin" />
  <div id="menuImg">
    <div><a href="listemissions?prevUrl=accueil"><img src="assets/img/410344.jpg" alt="Missions" class="theme" /></a></div>
    <div><a href="creationMission?prevUrl=accueil"><img src="assets/img/821082.png" alt="Bureau" class="theme" /></a></div>
    <div><a href="profil?prevUrl=accueil"><img src="assets/img/741930.jpg" alt="Profil" class="theme" /></a></div>
  </div>
  <div id="themeTitleContainer">
    <div class="themeTitleSub">
      <div class="themeTitle">Missions</div>
      <hr class="themeTitleSeparator" />
      <div class="themeTitleText">
        Choisissez une mission et <br />
        partez à l'aventure dans <br />
        une équipe de 5 soldats.
      </div>
    </div>
    <div class="themeTitleSub">
      <div class="themeTitle">Bureau</div>
      <hr class="themeTitleSeparator" />
      <div class="themeTitleText">
        Créez des missions pour<br />
        donner de l'expérience aux<br />
        soldats du campement.
      </div>
    </div>
    <div class="themeTitleSub">
      <div class="themeTitle">Profil</div>
      <hr class="themeTitleSeparator" />
      <div class="themeTitleText">
        Consultez votre profil pour<br />
        voir vos points d'expérience<br />
        sur chaque poste.
      </div>
    </div>
  </div>
</div>