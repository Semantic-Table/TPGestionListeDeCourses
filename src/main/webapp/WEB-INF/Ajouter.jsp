<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stagiaire
  Date: 05/07/2022
  Time: 08:13
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
    <title>Ajouter</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Ajouter"/>
</jsp:include>
<main>
    <form id="creerliste" action="EditerListe" method="post">
        <div class="align"><p>Nom: </p><input type="text" name="nomListe" value="${liste.nom}"></div>
        <c:forEach items="${listearticles}" var="listearticle">
            <c:forEach items="${articles}" var="article">
                <c:if test="${listearticle.ID_article == article.ID_article}">
                    <div class="align"><p>${article.nom}</p>
                        <a href="DeleteArticle?delete=true&nom=${article.nom}&nomListe=${liste.nom}"><img src="img/croix.png"
                                                                                                          alt=""></a></div>

                </c:if>
            </c:forEach>
        </c:forEach>
        <div class="align"><p>Article: </p><input type="text" name="nomArticle"></div>
        <button id="ajouterarticle" type="submit">ajouter article</button>
        </a>
    </form>
</main>
<footer>
    <a href="Accueil"><img src="img/arrowright.png" alt=""></a>
</footer>
</body>
</html>
