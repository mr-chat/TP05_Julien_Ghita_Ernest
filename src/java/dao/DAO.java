/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.MySQLConnection;
import java.sql.Connection;

/**
 *
 * @author stag
 */
    public abstract class DAO<T> {

        protected Connection connection = MySQLConnection.getInstance();

        /**
         * Permet de récupérer un objet via son ID
         *
         * @param id
         * @return l ’ objet r echerché
         */
        public abstract T find(Long id);

        /**
         *
         * }

*
         * @param obj
         * @return l ’ objet créé avec son id
         */
        public abstract T create(T obj);

        /**
         * Permet de mettre à jour les données d'une entrée dans la base
         *
         * @param obj
         * @return l ’ objet modifié
         */
        public abstract T update(T obj);

        /**
         * Permet la suppression d'une entrée de la base
         *
         * @param obj
         */
        public abstract void delete(T obj);

}
