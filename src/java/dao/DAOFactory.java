/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Utilisateur;

/**
 *
 * @author Ghita Kardoudi Pour une evolution éventuelle du projet : on garde la
 * factory
 */
public class DAOFactory {
    public static DAO<Utilisateur> getUtilisateurDAO() {
        return new UtilisateurDAO();
    }

}
