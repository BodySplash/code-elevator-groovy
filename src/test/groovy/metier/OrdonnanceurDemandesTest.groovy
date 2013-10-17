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
        ordonnanceur.appelle(1, Direction.HAUT)
        ordonnanceur.appelle(1, Direction.HAUT)
        ordonnanceur.arrivé(1)

        def étage = ordonnanceur.prochainÉtage(1)

        assert étage.present == false
    }

    @Test
    public void unGoEstPrioritaireSurUnAppel() {
        ordonnanceur.appelle(1, Direction.BAS)
        ordonnanceur.go(2)

        def étage = ordonnanceur.prochainÉtage(0)

        assert étage.get() == 2
    }

    @Test
    public void peutPrioriserUnAppilSIlEstSurLaRoute() {
        ordonnanceur.go(2)
        ordonnanceur.appelle(1, Direction.HAUT)

        def étage = ordonnanceur.prochainÉtage(0)

        assert étage.get() == 1
    }

    @Test
    public void peutPrioriserDansLeBonSens() {
        ordonnanceur.go(1)
        ordonnanceur.appelle(2, Direction.BAS)

        def étage = ordonnanceur.prochainÉtage(3)

        assert étage.get() == 2
    }

    @Test
    public void peutOrdonnerLesOrdres() {
        ordonnanceur.go(2)
        ordonnanceur.go(1)

        def étage = ordonnanceur.prochainÉtage(0)

        assert étage.get() == 1
    }
}
