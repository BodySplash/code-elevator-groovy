package metier

class EtatEnMouvement extends Ascenseur.EtatAscenseur {

    EtatEnMouvement(def ascenseur) {
        super(ascenseur)
    }

    @Override
    String prochaineCommande() {
        if (prochainEtage().or(ascenseur.etage) != ascenseur.etage) {
            def direction = direction(prochainEtage().get())
            direction.deplace(ascenseur)
            return direction.commande
        }
        ascenseur.arrive()
        return "OPEN"

    }


    private def direction(cible) {
        if(cible > ascenseur.etage)
            return Direction.HAUT
        return Direction.BAS
    }

}
