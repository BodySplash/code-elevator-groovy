package metier

import com.google.common.base.Optional

class OrdonnanceurDemandes {

    private final appels = []
    private final ordres = []

    Optional<Integer> prochainÉtage(def étageCourant) {
        def résultat = prochainOrdre().or(prochainAppel())
        if (résultat.present) {
            return Optional.of(cheminEntre(étageCourant, résultat)[0])
        }
        return résultat
    }

    def cheminEntre(def étageCourant, Optional<Integer> cible) {
        def direction = Direction.entre(étageCourant, cible.get())
        def range = étageCourant..cible.get()
        def chemin = appels.takeWhile {
            it.étage in range && it.direction == direction
        }
        .collect { it.étage }
        chemin.addAll(ordres.takeWhile { it in range})
        chemin.add(cible.get())
        chemin.unique()
        chemin.sort(direction.&compare)
        return chemin
    }

    private Optional<Integer> prochainAppel() {
        if (appels.empty)
            return Optional.absent()
        return Optional.of(appels[0].étage)
    }

    private Optional<Integer> prochainOrdre() {
        if (ordres.empty)
            return Optional.absent()
        return Optional.of(ordres[0])
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
