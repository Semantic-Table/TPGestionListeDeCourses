package fr.afpa.tpgestionlistedecourses.dal;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.bo.ListeArticle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleListeSQL {
    public void insert(int ID_article,int ID_liste, boolean actif) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO listearticle(ID_article, ID_liste, actif) VALUES (?,?, ?)"
            );
            pstmt.setInt(1, ID_article);
            pstmt.setInt(2, ID_liste);
            pstmt.setBoolean(3, actif);
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ListeArticle> selectOneList(int ID_liste) {
        ArrayList<ListeArticle> listearticles = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, ID_article, actif FROM listearticle WHERE ID_liste = ?"
            );
            pstmt.setInt(1,ID_liste);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listearticles.add(new ListeArticle(
                        rs.getInt("ID_liste"), rs.getInt("ID_article"),rs.getBoolean("actif")
                ));
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listearticles;
    }
    public void update(ListeArticle listeArticle) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE listearticle SET actif = ? WHERE ID_liste = ? AND ID_article = ?"
            );
            System.out.println("test");
            pstmt.setBoolean(1, !listeArticle.isActif());
            pstmt.setInt(2, listeArticle.getID_liste());
            pstmt.setInt(3, listeArticle.getID_article());
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void resetAll(Liste liste) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE listearticle SET actif = 1 WHERE ID_liste = ?"
            );
            System.out.println("test");
            pstmt.setInt(1, liste.getID_liste());
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ListeArticle selectOne(int ID_liste,int ID_article) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_article,ID_liste,actif FROM listearticle WHERE ID_liste = ? AND ID_article = ?"
            );

            pstmt.setInt(1, ID_liste);
            pstmt.setInt(2, ID_article);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                ListeArticle listeArticle = new ListeArticle(rs.getInt("ID_liste"), rs.getInt("ID_article"), rs.getBoolean("actif"));
                rs.close();
                connection.close();
                pstmt.close();
                return listeArticle;
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
