package metier

import com.google.common.base.Optional

class OrdonnanceurDemandes {

    private final appels = []
    private final ordres = []

    Optional<Integer> prochainÉtage(def étageCourant) {
        def résultat = prochainOrdre().or(prochainAppel())
        if (résultat.present) {
            return Optional.of(cheminEntre(étageCourant, résultat.get())[0])
        }
        return résultat
    }

    private Optional<Integer> prochainOrdre() {
        return Optional.fromNullable(ordres[0])
    }

    private Optional<Integer> prochainAppel() {
        return Optional.fromNullable(appels[0]?.étage)
    }

    def cheminEntre(def étageCourant, def cible) {
        def direction = Direction.entre(étageCourant, cible)
        def range = étageCourant..cible
        def chemin = appelsSurLaRoute(range, direction) +  ordresSurLaRoute(range) + cible
        chemin.unique()
        chemin.sort(direction.&comparateur)
        return chemin
    }

    private appelsSurLaRoute(range, direction) {
        appels.takeWhile {
            it.étage in range && it.direction == direction
        }
        .collect { it.étage }
    }

    private ordresSurLaRoute(range) {
        ordres.takeWhile { it in range }
    }

    def appelle(def étage, def direction) {
        def appel = new Expando(étage: étage, direction: direction)
        appels << appel
        appels.unique()
    }

    def go(def étage) {
        ordres << étage
        ordres.unique()
    }

    def arrivé(def étage) {
        appels.removeAll {
            it.étage == étage
        }
        ordres.remove((Object) étage)
    }
}
