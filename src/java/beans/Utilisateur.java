/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author Julien C
 */
public class Utilisateur {

    private String email;
    private Long id;
    private String nom;
    private String motDePasse;

    public Utilisateur() {
    }

    public Utilisateur(Long id, String nom, String email, String motDePasse) {
        this.email = email;
        this.id = id;
        this.nom = nom;
        this.motDePasse = motDePasse;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
