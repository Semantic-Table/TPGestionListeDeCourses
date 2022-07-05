<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Accueil</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Accueil"/>
</jsp:include>
<main id="mainAccueil">
    <c:forEach items="${listes}" var="liste">
        <div class="liste align">
            <div><a href="EditerListe?nomListe=${liste.nom}"><p>Liste : ${liste.nom}</p></a></div>
            <div class="rightList">
                <a href="AfficherListe?ID_liste=${liste.ID_liste}">
                    <img src="img/cart.png" alt="">
                </a>
                <form action="Accueil?delete=true&nom=${liste.nom}" method="post">
                    <button type="submit" class="article"><img src="img/croix.png" alt=""></button>
                </form>
            </div>

        </div>
    </c:forEach>

</main>
<footer>
    <a href="CreerListe?nomListe="><img src="img/plus.png" alt=""></a>
</footer>
</body>
</html>