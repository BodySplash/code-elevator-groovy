package metier

import com.google.common.base.Optional

class OrdonnanceurDemandes {

    private final appels = []
    private final ordres = []

    Optional<Integer> prochainÉtage() {
        return prochainOrdre().or(prochainAppel())
    }

    private Optional<Integer> prochainAppel() {
        if (appels.empty)
            return Optional.absent()
        return Optional.of(appels[0])
    }

    private Optional<Integer> prochainOrdre() {
        if (ordres.empty)
            return Optional.absent()
        return Optional.of(ordres[0])
    }

    def appelle(def étage, def direction) {
        appels.add(étage)
        appels.unique()
    }

    def go(def étage) {
        ordres.add(étage)
        ordres.unique()
    }

    def arrivé(def étage) {
        appels.remove((Object) étage)
        ordres.remove((Object) étage)
    }
}
