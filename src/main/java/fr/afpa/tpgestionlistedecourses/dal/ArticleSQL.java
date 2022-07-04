package fr.afpa.tpgestionlistedecourses.dal;

import fr.afpa.tpgestionlistedecourses.bo.Article;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticleSQL {
    public ArrayList<Article> selectAll() {
        ArrayList<Article> articles = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_article, nom FROM article"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt("ID_article"), rs.getString("nom")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }
    public Article selectOne(int ID_article) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_article, nom FROM article where ID_article = ?"
            );
            pstmt.setInt(1, ID_article);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article(rs.getInt("ID_article"), rs.getString("nom"));
                return article;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void insert(String nom) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO article(nom) VALUES (?)"
            );
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
