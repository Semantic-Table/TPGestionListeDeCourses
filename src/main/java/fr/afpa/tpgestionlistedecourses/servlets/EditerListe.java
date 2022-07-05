package fr.afpa.tpgestionlistedecourses.servlets;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.bo.ListeArticle;
import fr.afpa.tpgestionlistedecourses.dal.ArticleListeSQL;
import fr.afpa.tpgestionlistedecourses.dal.ArticleSQL;
import fr.afpa.tpgestionlistedecourses.dal.ListeSQL;
import fr.afpa.tpgestionlistedecourses.exceptions.VideException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "EditerListe", value = "/EditerListe")
public class EditerListe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListeSQL listeSQL = new ListeSQL();
        Liste liste = listeSQL.selectOne(request.getParameter("nomListe"));
        ArticleSQL articleSQL = new ArticleSQL();
        ArticleListeSQL articleListeSQL = new ArticleListeSQL();
        ArrayList<ListeArticle> listeArticles = articleListeSQL.selectOneList(liste.getID_liste());
        ArrayList<Article> articles = articleSQL.selectAll();
        request.setAttribute("liste", liste);
        request.setAttribute("listearticles", listeArticles);
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("WEB-INF/Ajouter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListeSQL listeSQL = new ListeSQL();
        Liste liste = listeSQL.selectOne(request.getParameter("nomListe"));

        ArticleSQL articleSQL = new ArticleSQL();
        Article article;
        ArticleListeSQL articleListeSQL = new ArticleListeSQL();
        try {


            if (liste == null) {
                listeSQL.insert(request.getParameter("nomListe"));
                liste = listeSQL.selectLast();
            }
            if (articleSQL.selectOne(request.getParameter("nomArticle")) == null) {
                articleSQL.insert(request.getParameter("nomArticle"));
                article = articleSQL.selectLast();
            } else {
                article = articleSQL.selectOne(request.getParameter("nomArticle"));
            }
            System.out.println(liste);
            articleListeSQL.insert(article.getID_article(), liste.getID_liste(), true);




        } catch (VideException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<Article> articles = articleSQL.selectAll();
        if (liste != null){
            ArrayList<ListeArticle> listeArticles = articleListeSQL.selectOneList(liste.getID_liste());
            request.setAttribute("listearticles", listeArticles);
        }

        request.setAttribute("liste", liste);

        request.setAttribute("articles", articles);

        request.getRequestDispatcher("WEB-INF/Ajouter.jsp").forward(request, response);
    }
}
