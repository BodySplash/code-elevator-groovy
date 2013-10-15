package metier

class EtatEnMouvement extends Ascenseur.EtatAscenseur {

    EtatEnMouvement(def ascenseur) {
        super(ascenseur)
    }

    @Override
    String prochaineCommande() {
        if (prochainÉtage().or(ascenseur.étage) != ascenseur.étage) {
            def direction = direction(prochainÉtage().get())
            direction.déplace(ascenseur)
            return direction.commande
        }
        ascenseur.arrivé()
        return "OPEN"

    }


    private def direction(cible) {
        if(cible > ascenseur.étage)
            return Direction.HAUT
        return Direction.BAS
    }

}
