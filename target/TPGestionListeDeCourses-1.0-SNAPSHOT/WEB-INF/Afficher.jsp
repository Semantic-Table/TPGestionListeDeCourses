<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 05/07/2022
  Time: 08:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Afficher</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Afficher"/>
</jsp:include>
<main id="mainAfficher">
    <div>${liste.nom}</div>
    <div>
        <c:forEach items="${listearticles}" var="listearticle">
            <c:forEach items="${articles}" var="article">
                <c:if test="${listearticle.ID_article == article.ID_article}">
                    <div>
                        <form class="align" action="AfficherListe?ID_liste=${liste.ID_liste}&ID_article=${article.ID_article}"
                              method="post">
                            <button class="article" type="submit">
                        <p
                                <c:if test="${!listearticle.actif}">class="barre"</c:if> >● ${article.nom}</p>
                        </button></form>
                    </div>

                </c:if>
            </c:forEach>
        </c:forEach>
    </div>
</main>
<footer>
    <a href="Accueil"><img src="img/arrowleft.png" alt=""></a><a href="ResetListe?ID_liste=${liste.ID_liste}"><img
        src="img/eraser.png" alt=""></a>
</footer>
</body>
</html>