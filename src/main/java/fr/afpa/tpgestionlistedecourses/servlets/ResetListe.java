package fr.afpa.tpgestionlistedecourses.servlets;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.bo.ListeArticle;
import fr.afpa.tpgestionlistedecourses.dal.ArticleListeSQL;
import fr.afpa.tpgestionlistedecourses.dal.ArticleSQL;
import fr.afpa.tpgestionlistedecourses.dal.ListeSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ResetListe", value = "/ResetListe")
public class ResetListe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Liste liste;
        ListeSQL listeSQL = new ListeSQL();
        liste = listeSQL.selectOne(Integer.parseInt(request.getParameter("ID_liste")));
        ArticleListeSQL articleListeSQL = new ArticleListeSQL();
        ArticleSQL articleSQL = new ArticleSQL();
        ArrayList<Article> articles = articleSQL.selectAll();
        articleListeSQL.resetAll(liste);

        ArrayList<ListeArticle> listeArticles = articleListeSQL.selectOneList(liste.getID_liste());
        System.out.println("coucou");
        request.setAttribute("liste",liste);
        request.setAttribute("articles",articles);
        request.setAttribute("listearticles" ,listeArticles);
        request.getRequestDispatcher("WEB-INF/Afficher.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
