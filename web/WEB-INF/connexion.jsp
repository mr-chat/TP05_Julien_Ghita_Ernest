<%-- 
    Document   : connexion
    Created on : 22 mai 2017, 16:15:06
    Author     : Ernest Kolingba
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Connexion</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>"/>
    </head>
    <body>
        <form method="post" action="connexion">
            <fieldset>
                <legend>Connexion</legend>
                <p>Vous pouvez vous connecter via ce formulaire.</p>
                <label for="nom">Nom <span class="requis">*</span></label>
                <input type="nom" id="email" name="nom" value="<c:out value="${utilisateur.nom}"/>" size="20" maxlength="60" />
                <span class="erreur">${form.erreurs['nom']}</span>
                <br /><label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                <span class="erreur">${form.erreurs['motdepasse']}</span>
                <br /><input type="submit" value="Connexion" class="sansLabel" />
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.sessionUtilisateur}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son nom --%>
                    <p class="succes">${sessionScope.sessionUtilisateur.nom}</p>   
                    <p class="succes">connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.email}</p>
                    <br>
                    <a href="deconnexion" class="requis">deconnexion</a>
                </c:if>
                    
            </fieldset>
        </form>
    </body>
</html>