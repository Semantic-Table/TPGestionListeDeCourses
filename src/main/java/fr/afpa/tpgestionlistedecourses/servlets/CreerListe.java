package fr.afpa.tpgestionlistedecourses.servlets;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.dal.ArticleListeSQL;
import fr.afpa.tpgestionlistedecourses.dal.ArticleSQL;
import fr.afpa.tpgestionlistedecourses.dal.ListeSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CreerListe", value = "/CreerListe")
public class CreerListe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/Ajouter.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ListeSQL listeSQL = new ListeSQL();
        Liste liste = listeSQL.selectOne(request.getParameter("nom"));
        ArticleSQL articleSQL = new ArticleSQL();
        ArticleListeSQL articleListeSQL = new ArticleListeSQL();
        if (liste == null) {
            listeSQL.insert(request.getParameter("nom"));
            liste = listeSQL.selectLast();
        }

    }
}
