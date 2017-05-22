/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

/**
 *
 * @author Julien C
 */
import dao.DAO;
import dao.DAOFactory;
import beans.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import outils.Outils;

public final class InscriptionForm {


    /* Des constantes */
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_CONF = "confirmation";
    private static final String CHAMP_NOM = "nom";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur inscrireUtilisateur(HttpServletRequest request) {
        String email = Outils.getValeurChamp(request, CHAMP_EMAIL);
        String motDePasse = Outils.getValeurChamp(request, CHAMP_PASS);
        String confirmation = Outils.getValeurChamp(request, CHAMP_CONF);
        String nom = Outils.getValeurChamp(request, CHAMP_NOM);
        Utilisateur utilisateur = new Utilisateur();
        try {
            validationEmail(email);
        } catch (Exception e) {
            setErreur(CHAMP_EMAIL, e.getMessage());
        }
        utilisateur.setEmail(email);
        try {
            validationMotsDePasse(motDePasse, confirmation);
        } catch (Exception e) {
            setErreur(CHAMP_PASS, e.getMessage());
            setErreur(CHAMP_CONF, null);
        }
        utilisateur.setMotDePasse(motDePasse);
        try {
            validationNom(nom);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }
        utilisateur.setNom(nom);
        if (erreurs.isEmpty()) {
            resultat = "Succès de l'inscription.";

        } else {
            resultat = "Échec de l'inscription.";
        }
        return utilisateur;
    }

    private void validationEmail(String email) throws Exception {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide."
                );
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    private void validationMotsDePasse(String motDePasse, String confirmation
    ) throws Exception {
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (motDePasse.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    /*
    *
    * Vérifie si le nom d'utilisateur existe déjà
    *
     */
    private void validationNom(String nom) throws Exception {
        DAO<Utilisateur> utilisateur = DAOFactory.getUtilisateurDAO();
        if (nom != null && nom.length() < 2) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 2 caractères.");
        } else if (utilisateur.findByName(nom) != null) {
            throw new Exception("Ce nom d'utilisateur existe déjà");
        }
    }


    /*
* Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }

}
