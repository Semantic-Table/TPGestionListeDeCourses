package fr.afpa.tpgestionlistedecourses.servlets;

import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.dal.ListeSQL;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Accueil", value = "/Accueil")
public class Accueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Liste> listes;
        ListeSQL listeSQL = new ListeSQL();
        listes = listeSQL.selectAll();
        request.setAttribute("listes", listes);
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (request.getParameter("delete").equals("true")){
           System.out.println("test");
           String nom = request.getParameter("nom");
           ListeSQL listeSQL = new ListeSQL();
           listeSQL.delete(nom);

           ArrayList<Liste> listes;
           listes = listeSQL.selectAll();
           request.setAttribute("listes", listes);
       }


        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request,response);
    }
}
