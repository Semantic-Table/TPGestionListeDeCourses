package fr.afpa.tpgestionlistedecourses.dal;

import fr.afpa.tpgestionlistedecourses.bo.Article;
import fr.afpa.tpgestionlistedecourses.bo.Liste;
import fr.afpa.tpgestionlistedecourses.exceptions.VideException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListeSQL {
    public ArrayList<Liste> selectAll() {
        ArrayList<Liste> listes = new ArrayList<>();
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, nom FROM liste"
            );
            ResultSet rs = pstmt.executeQuery();


            while (rs.next()) {

                listes.add(new Liste(
                        rs.getInt("ID_liste"), rs.getString("nom")
                ));
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(listes);
        return listes;
    }

    public Liste selectOne(int ID_liste) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, nom FROM liste where ID_liste = ?"
            );
            pstmt.setInt(1, ID_liste);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt("ID_liste"), rs.getString("nom"));
                rs.close();
                connection.close();
                pstmt.close();
                return liste;
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Liste selectOne(String nom) {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, nom FROM liste where nom = ?"
            );
            pstmt.setString(1, nom);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt("ID_liste"), rs.getString("nom"));
                rs.close();
                connection.close();
                pstmt.close();
                return liste;
            }
            rs.close();
            connection.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Liste selectLast() {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT ID_liste, nom FROM liste ORDER BY ID_liste desc LIMIT 1"
            );
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Liste liste = new Liste(rs.getInt("ID_liste"), rs.getString("nom"));
                rs.close();
                connection.close();
                pstmt.close();
                return liste;
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
                    "INSERT INTO liste(nom) VALUES (?)"
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
                    "DELETE FROM liste WHERE nom = ?"
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
