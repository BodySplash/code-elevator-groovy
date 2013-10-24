package metier

import com.google.common.base.Optional

class EtatFerme extends Ascenseur.EtatAscenseur{

    EtatFerme(def ascenseur) {
        super(ascenseur)
    }

    @Override
    String prochaineCommande() {
        Optional<Integer> etage = prochainEtage()
        if(!etage.present)
            return "NOTHING"
        ascenseur.etat = new EtatEnMouvement(ascenseur)
        return ascenseur.etat.prochaineCommande()
    }

}
