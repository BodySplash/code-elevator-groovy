package metier

import com.google.common.base.Optional

class EtatFerme extends Ascenseur.EtatAscenseur{

    EtatFerme(def ascenseur) {
        super(ascenseur)
    }

    @Override
    String prochaineCommande() {
        Optional<Integer> étage = prochainÉtage()
        if(!étage.present)
            return "NOTHING"
        ascenseur.état = new EtatEnMouvement(ascenseur)
        return ascenseur.état.prochaineCommande()
    }

}
