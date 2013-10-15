package metier

import com.google.common.base.Optional

class Ascenseur {


    protected int étage = 0;
    protected EtatAscenseur état = new EtatFerme(this)
    protected OrdonnanceurDemandes ordonnanceurDemandes = new OrdonnanceurDemandes()

    void appelle(def étageCible, def direction) {
        ordonnanceurDemandes.appelle(étageCible, direction)
    }

    void go(def étage) {
        ordonnanceurDemandes.go(étage);
    }

    String prochaineCommande() {
        return état.prochaineCommande();
    }

    void reset() {
        étage = 0;
        état = new EtatFerme(this)
        ordonnanceurDemandes = new OrdonnanceurDemandes()
    }

    void arrivé() {
        état = new EtatOuvert(this)
        ordonnanceurDemandes.arrivé(étage)
    }

    void descend() {
        étage--
    }

    void monte() {
        étage++
    }

    public static abstract class EtatAscenseur {

        protected final def ascenseur

        EtatAscenseur(def ascenseur) {
            this.ascenseur = ascenseur
        }

        abstract String prochaineCommande()

        protected Optional<Integer> prochainÉtage() {
            return ascenseur.ordonnanceurDemandes.prochainÉtage()
        }

    }
}
