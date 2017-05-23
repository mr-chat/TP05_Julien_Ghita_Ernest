/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Utilisateur;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghita Kardoudi
 */
public class UtilisateurDAO extends DAO<Utilisateur> {

    private final String TABLE = "utilisateurs";

    @Override
    public Utilisateur find(Long id) {
        Utilisateur utilisateur = null;

        try {
            String req = "SELECT * FROM " + TABLE + " WHERE id = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt
                    = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                utilisateur = new Utilisateur(
                        id,
                        result.getString("nom"),
                        result.getString("email"),
                        result.getString("motDePasse")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return utilisateur;
    }

    /**
     *
     * @param nom Rechreche par nom
     * @return Utilisateur avec ce nom
     */
    public Utilisateur findByName(String nom) {
        Utilisateur utilisateur = null;

        try {
            String req = "SELECT * FROM " + TABLE + " WHERE nom = ?";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt
                    = this.connection.prepareStatement(req);
            pstmt.setString(1, nom);
            ResultSet result = pstmt.executeQuery();
            if (result.first()) {
                utilisateur = new Utilisateur(
                        result.getLong("id"),
                        nom,
                        result.getString("email"),
                        result.getString("motDePasse")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return utilisateur;
    }

    /**
     * *
     *
     * @param obj Utilisateur
     * @return Obj Utilisateur
     */
    @Override
    public Utilisateur create(Utilisateur obj) {
        try {

            String req = "INSERT INTO " + TABLE + " (nom,email,motDePasse) VALUES(?,?,?)";
//            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt = this.connection.prepareStatement(req,
                    Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getEmail());
            pstmt.setString(3, obj.getMotDePasse());
// On soumet la requête et on récupère le nombre d'id créés
            int id = pstmt.executeUpdate();
// On pourrait s'arrêter là, mais  je préfère récupérer la ligne créée // Ca permet de savoir ce qu'on a réellement mis dans la DB

            ResultSet rs = pstmt.getGeneratedKeys();
            long last_inserted_id;
            if (rs.first()) { // Si on a des id créés
                last_inserted_id = rs.getInt(1);
// On récupère l'enregistrement créé
                obj = this.find(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override

    public Utilisateur update(Utilisateur obj) {
        try {
            String req = "UPDATE " + TABLE + " SET nom = ?, email = ?, "
                    + "motDePasse = ? WHERE id = ?";
//
            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt
                    = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getNom());
            pstmt.setString(2, obj.getEmail());
            pstmt.setString(3, obj.getMotDePasse());
            pstmt.setLong(4, obj.getId());

            pstmt.executeUpdate();
// On récupère l'enregistrement modifié
            obj = this.find(obj.getId());
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return obj;
    }
    /**
     * *
     *
     * @param obj
     */
    @Override
    public void delete(Utilisateur obj) {
        try {
            String req = "DELETE FROM " + TABLE + " WHERE id = ?";
            //            System.out.println("requête : " + req); // Debug
            PreparedStatement pstmt
                    = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilisateurDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }


}
