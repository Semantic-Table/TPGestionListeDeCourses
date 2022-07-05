package fr.afpa.tpgestionlistedecourses.dal;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.exceptions.VideException;


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
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return articles;
    }

    public Article selectOne(String nom) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_article, nom FROM article where nom = ?"
            );
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article(rs.getInt("ID_article"), rs.getString("nom"));
                rs.close();
                connection.close();
                pstmt.close();
                return article;
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Article selectLast() {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_article, nom FROM article ORDER BY ID_article desc LIMIT 1"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Article article = new Article(rs.getInt("ID_article"), rs.getString("nom"));
                rs.close();
                connection.close();
                pstmt.close();
                return article;
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void insert(String nom) throws VideException {
        if (nom.equals("")){
            throw new VideException("nom vide");
        }
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO article(nom) VALUES (?)"
            );
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void delete(String nom) {
        try {
            Connection connection = ConnectionProvider.getConnection();

            PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM article WHERE nom = ?"
            );
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
