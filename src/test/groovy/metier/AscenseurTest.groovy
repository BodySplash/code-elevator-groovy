package metier

import org.junit.Before
import org.junit.Test

class AscenseurTest {
    private Ascenseur ascenceur

    @Before
    public void setUp() {
        ascenceur = new Ascenseur()
    }

    @Test
    public void neFaitRienParDéfaut() {
        def commande = ascenceur.prochaineCommande()

        assert commande == "NOTHING"

    }

    @Test
    public void peutMonter() {
        ascenceur.appelle(3, "UP")

        def commande = ascenceur.prochaineCommande()

        assert commande == "UP"
    }

    @Test
    public void peutOuvrirLesPortes() {
        ascenceur.appelle(1, "UP")
        ascenceur.prochaineCommande()

        def commande = ascenceur.prochaineCommande()

        assert commande == "OPEN"
    }

    @Test
    public void peutFermerLesPortes() {
        ascenceur.appelle(1, "UP")
        ascenceur.prochaineCommande()
        ascenceur.prochaineCommande()

        def commande = ascenceur.prochaineCommande()

        assert commande == "CLOSE"

    }

    @Test
    public void peutSeSouvenirDesAppels() {
        ascenceur.appelle(1, "UP")
        ascenceur.appelle(2, "UP")

        ascenceur.prochaineCommande()
        def commande = ascenceur.prochaineCommande()

        assert commande == "OPEN"
    }

    @Test
    public void peutDescendre() {
        ascenceur.appelle(1, "UP")
        ascenceur.prochaineCommande()
        ascenceur.prochaineCommande()
        ascenceur.prochaineCommande()
        ascenceur.appelle(0, "UP")

        def commande = ascenceur.prochaineCommande()

        assert commande == "DOWN"
    }

    @Test
    public void peutDemanderAAllerÀUnÉtage() {
        ascenceur.go(1)

        def commande = ascenceur.prochaineCommande()

        assert commande == "UP"
    }

    @Test
    public void peutÊtreAppeléÀSonÉtageCourant() {
        ascenceur.appelle(0, "UP")

        def commande = ascenceur.prochaineCommande()

        assert commande == "OPEN"

    }
}
