package fr.afpa.tpgestionlistedecourses.servlets;

import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.dal.ListeSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AfficherListe", value = "/AfficherListe")
public class AfficherListe extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Liste liste;
        ListeSQL listeSQL = new ListeSQL();
        liste = listeSQL.selectOne(Integer.parseInt(request.getParameter("ID_liste")));
        request.setAttribute("liste",liste);
        request.getRequestDispatcher("WEB-INF/Afficher.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
