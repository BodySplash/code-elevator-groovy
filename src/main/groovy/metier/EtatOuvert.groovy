package metier

class EtatOuvert extends Ascenseur.EtatAscenseur{

    EtatOuvert(def ascenseur) {
        super(ascenseur)
    }

    @Override
    String prochaineCommande() {
        ascenseur.etat = new EtatFerme(ascenseur)
        return "CLOSE"
    }

}
