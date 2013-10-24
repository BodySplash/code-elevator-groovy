package metier

import com.google.common.base.Optional

class Ascenseur {


    protected int etage = 0;
    protected EtatAscenseur etat = new EtatFerme(this)
    protected OrdonnanceurDemandes ordonnanceurDemandes = new OrdonnanceurDemandes()

    void appelle(def etageCible, def direction) {
        ordonnanceurDemandes.appelle(etageCible, direction)
    }

    void go(def etage) {
        ordonnanceurDemandes.go(etage);
    }

    String prochaineCommande() {
        return etat.prochaineCommande();
    }

    void reset() {
        etage = 0;
        etat = new EtatFerme(this)
        ordonnanceurDemandes = new OrdonnanceurDemandes()
    }

    void arrive() {
        etat = new EtatOuvert(this)
        ordonnanceurDemandes.arrive(etage)
    }

    void descend() {
        etage--
    }

    void monte() {
        etage++
    }

    public static abstract class EtatAscenseur {

        protected final def ascenseur

        EtatAscenseur(def ascenseur) {
            this.ascenseur = ascenseur
        }

        abstract String prochaineCommande()

        protected Optional<Integer> prochainEtage() {
            return ascenseur.ordonnanceurDemandes.prochainEtage(ascenseur.etage)
        }

    }
}
