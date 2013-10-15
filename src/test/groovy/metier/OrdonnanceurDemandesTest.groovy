package metier

import org.junit.Before
import org.junit.Test

class OrdonnanceurDemandesTest {
    private OrdonnanceurDemandes ordonnanceur

    @Before
    public void setUp() throws Exception {
        ordonnanceur = new OrdonnanceurDemandes()

    }

    @Test
    public void nePrendPasEnCompteDeuxFoisLaMêmeDemande() {
        ordonnanceur.appelle(1, "UP")
        ordonnanceur.appelle(1, "UP")
        ordonnanceur.arrivé(1)

        def étage = ordonnanceur.prochainÉtage()

        assert étage.present == false
    }

    @Test
    public void unGoEstPrioritaireSurUnAppel() {
        ordonnanceur.appelle(1, "UP")
        ordonnanceur.go(2)

        def étage = ordonnanceur.prochainÉtage()

        assert étage.get() == 2
    }
}
