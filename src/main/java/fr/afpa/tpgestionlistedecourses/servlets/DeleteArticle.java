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

@WebServlet(name = "DeleteArticle", value = "/DeleteArticle")
public class DeleteArticle extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListeSQL listeSQL = new ListeSQL();
        Liste liste = listeSQL.selectOne(request.getParameter("nomListe"));
        ArticleSQL articleSQL = new ArticleSQL();
        ArticleListeSQL articleListeSQL = new ArticleListeSQL();
        articleSQL.delete(request.getParameter("nom"));
        System.out.println(liste.getID_liste());
        if (articleListeSQL.selectOneList(liste.getID_liste()).isEmpty()){
            ArrayList<Liste> listes = listeSQL.selectAll();
            request.setAttribute("listes",listes);
            request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
        }
        ArrayList<ListeArticle> listeArticles = articleListeSQL.selectOneList(liste.getID_liste());
        ArrayList<Article> articles = articleSQL.selectAll();
        request.setAttribute("liste", liste);
        request.setAttribute("listearticles", listeArticles);
        request.setAttribute("articles", articles);
        request.getRequestDispatcher("WEB-INF/Ajouter.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
