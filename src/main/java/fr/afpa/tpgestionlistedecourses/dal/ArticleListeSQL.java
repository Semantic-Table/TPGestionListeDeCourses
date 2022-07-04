package fr.afpa.tpgestionlistedecourses.dal;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.ListeArticle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleListeSQL {
    public void insert(int ID_article,int ID_liste) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO listearticle(ID_article, ID_liste) VALUES (?,?)"
            );
            pstmt.setInt(1, ID_article);
            pstmt.setInt(1, ID_liste);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ListeArticle> selectOneList(int ID_liste) {
        ArrayList<ListeArticle> listearticles = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, ID_article FROM listearticle"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                listearticles.add(new ListeArticle(
                        rs.getInt("ID_liste"), rs.getInt("ID_article")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listearticles;
    }
}
