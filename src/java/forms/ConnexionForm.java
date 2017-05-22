/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import dao.DAO;
import dao.DAOFactory;
import beans.Utilisateur;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import outils.Outils;

/**
 *
 * @author Julien C
 */
public class ConnexionForm {
    private static final String CHAMP_NOM = "nom";
    private static final String CHAMP_PASS = "motdepasse";
    private String resultat;
    private Map<String, String> erreurs = new HashMap<>();

    public String getResultat() {
        return resultat;
    }

    public Map<String, String> getErreurs() {
        return erreurs;
    }

    public Utilisateur connecterUtilisateur(HttpServletRequest request) {
        /* Récupération des champs du formulaire */
        String nom = Outils.getValeurChamp(request, CHAMP_NOM);
        String motDePasse = Outils.getValeurChamp(request, CHAMP_PASS);
        Utilisateur utilisateur = new Utilisateur();
        /* Validation du champ email. */
        try {
            utilisateur = validationIdentifiants(nom, motDePasse);
        } catch (Exception e) {
            setErreur(CHAMP_NOM, e.getMessage());
        }

        /* Initialisation du résultat global de la validation. */
        if (erreurs.isEmpty()) {
            resultat = "Succès de la connexion.";
        } else {
            resultat = "Échec de la connexion.";
        }
        return utilisateur;
    }

    /**
     * Valide le combo nom/motdepasse et retourne l'utilisateur connecté
     */
    private Utilisateur validationIdentifiants(String nom, String motDePasse) throws Exception {
        DAO<Utilisateur> utilisateur = DAOFactory.getUtilisateurDAO();
        Utilisateur utilisateurEnCours = utilisateur.findByName(nom);
        if (nom == null) {
            throw new Exception("Merci de saisir votre nom d'utilisateur.");
        } else if (utilisateurEnCours == null) {
            throw new Exception("Le nom d'utilisateur est invalide.");
        } else if (!utilisateurEnCours.getMotDePasse().equals(motDePasse)) {
            throw new Exception("Le mot de passe est invalide.");
        }
        return utilisateurEnCours;
    }


    /*
* Ajoute unmessage correspondant au champ spécifié à la map des
erreurs.
     */
    private void setErreur(String champ, String message) {
        erreurs.put(champ, message);
    }
}
