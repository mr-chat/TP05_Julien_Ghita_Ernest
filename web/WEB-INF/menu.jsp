
<html>
    <body>
        <div>Travail collaboratif : Ernest Ghita Julien </div>
        <c:choose>
            <c:when test="${sessionScope.sessionUtilisateur !=null}">
                <div><a href="<c:url value="deconnexion"/>">déconnexion</a></div>
            </c:when>
            <c:otherwise>
                <div><a href="<c:url value="inscription"/>">inscription</a></div>
                   <a href="<c:url value="connexion"/>">connexion</a></div>
            </c:otherwise>
        </c:choose>
    </body>
</html>
