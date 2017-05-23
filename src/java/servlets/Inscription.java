package servlets;

import beans.Utilisateur;
import forms.InscriptionForm;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Inscription extends HttpServlet {

    /* Des constantes */
    private static final String ATT_FORM = "form";//form = message qu'on met dans setAttribute
    private static final String ATT_USER = "utilisateur";//utilisateur = message que l'on met dans setAttribute
    public static final String VUE = "/WEB-INF/inscription.jsp";

    /* Requête GET sur notre formulaire => affichage du formulaire */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /* Soumission du formulaire => réception et contrôle des données */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /* Préparation de l'objet métier formulaire */
        //on instancie l'objet metier
        InscriptionForm form = new InscriptionForm();

        /*
         * Appel au traitement et à la validation de la requête, et récupération
         * du bean en résultant
         */
        //inscrireUtilisateur est la methode d'InscriptionForm qui fait tout le boulot
        Utilisateur utilisateur = form.inscrireUtilisateur(request);

        //si l’inscription est valide, l’utilisateur doit être immédiatement connecté.
        HttpSession session = request.getSession();
        if (form.getErreurs().isEmpty()) {
            session.setAttribute(ATT_USER, utilisateur);
        } else {
            session.setAttribute(ATT_FORM, null);
        }

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute(ATT_FORM, form);
        request.setAttribute(ATT_USER, utilisateur);

        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

}
