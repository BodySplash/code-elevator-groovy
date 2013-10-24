package metier

import com.google.common.base.Optional

class OrdonnanceurDemandes {

    private final appels = []
    private final ordres = []

    Optional<Integer> prochainEtage(def etageCourant) {
        def resultat = prochainOrdre().or(prochainAppel())
        if (resultat.present) {
            return Optional.of(cheminEntre(etageCourant, resultat.get())[0])
        }
        return resultat
    }

    private Optional<Integer> prochainOrdre() {
        return Optional.fromNullable(ordres[0])
    }

    private Optional<Integer> prochainAppel() {
        return Optional.fromNullable(appels[0]?.etage)
    }

    def cheminEntre(def etageCourant, def cible) {
        def direction = Direction.entre(etageCourant, cible)
        def range = etageCourant..cible
        def chemin = appelsSurLaRoute(range, direction) +  ordresSurLaRoute(range) + cible
        chemin.unique()
        chemin.sort(direction.&comparateur)
        return chemin
    }

    private appelsSurLaRoute(range, direction) {
        appels.takeWhile {
            it.etage in range && it.direction == direction
        }
        .collect { it.etage }
    }

    private ordresSurLaRoute(range) {
        ordres.takeWhile { it in range }
    }

    def appelle(def etage, def direction) {
        def appel = new Expando(etage : etage, direction : direction)
        appels << appel
        appels.unique()
    }

    def go(def etage) {
        ordres << etage
        ordres.unique()
    }

    def arrive(def etage) {
        appels.removeAll {
            it.etage == etage
        }
        ordres.remove((Object) etage)
    }
}
