/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outils;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Julien C
 *
 * toolbox créée dans l'optique de mettre à disposition de tout le projet des
 * fonctions universellement utiles. Dans ce cas une seule fonction et une seule
 * classe qui l'utilise, mais c'est pour la forme
 *
 */
public final class Outils {

    private Outils() {

    }

    public static String getValeurChamp(HttpServletRequest request, String nomChamp) {
        String valeur = request.getParameter(nomChamp);
        if (valeur == null || valeur.trim().length() == 0) {
            return null;
        } else {
            return valeur.trim();
        }
    }
}
