<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="apple-touch-icon" sizes="57x57" href="assets/favicon/apple-icon-57x57.png">
    <link rel="apple-touch-icon" sizes="60x60" href="assets/favicon/apple-icon-60x60.png">
    <link rel="apple-touch-icon" sizes="72x72" href="assets/favicon/apple-icon-72x72.png">
    <link rel="apple-touch-icon" sizes="76x76" href="assets/favicon/apple-icon-76x76.png">
    <link rel="apple-touch-icon" sizes="114x114" href="assets/favicon/apple-icon-114x114.png">
    <link rel="apple-touch-icon" sizes="120x120" href="assets/favicon/apple-icon-120x120.png">
    <link rel="apple-touch-icon" sizes="144x144" href="assets/favicon/apple-icon-144x144.png">
    <link rel="apple-touch-icon" sizes="152x152" href="assets/favicon/apple-icon-152x152.png">
    <link rel="apple-touch-icon" sizes="180x180" href="assets/favicon/apple-icon-180x180.png">
    <link rel="icon" type="image/png" sizes="192x192"  href="assets/favicon/android-icon-192x192.png">
    <link rel="icon" type="image/png" sizes="32x32" href="assets/favicon/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="96x96" href="assets/favicon/favicon-96x96.png">
    <link rel="icon" type="image/png" sizes="16x16" href="assets/favicon/favicon-16x16.png">
    <link rel="manifest" href="assets/favicon/manifest.json">
    <meta name="msapplication-TileColor" content="#ffffff">
    <meta name="msapplication-TileImage" content="/ms-icon-144x144.png">
    <meta name="theme-color" content="#ffffff">
    <title><%= (String) request.getAttribute("title") %></title>
</head>

<body>
    <div id='<%= (String) request.getAttribute("background_id") %>' class="background">
    </div>
    <%@ include file="header.jsp" %>
    <jsp:include page='<%= (String) request.getAttribute("view") %>' />
    <% if(request.getAttribute("succes") != null) { %>
        <div class="alert succes">
            <%= request.getAttribute("succes") %>
        </div>
    <% } %>
    <% if(request.getAttribute("erreur") != null) { %>
        <div class="alert error">
            <%= request.getAttribute("erreur") %>
        </div>
    <% } %>
    <script>
        var alerts = document.getElementsByClassName("alert");
        var timeout = 4000;
        
        setTimeout(() => {
            for (let index = 0; index < alerts.length; index++) {
                alerts[index].parentNode.removeChild(alerts[index]);
            }
        }, timeout);
    </script>
</body>

</html>