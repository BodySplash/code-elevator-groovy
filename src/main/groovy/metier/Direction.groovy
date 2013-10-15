package metier


public enum Direction {

    HAUT("UP") {

        void déplace(Ascenseur ascenseur) {
            ascenseur.monte()
        }
    }, BAS("DOWN") {

        void déplace(Ascenseur ascenseur) {
            ascenseur.descend()
        }
    };

    final String commande

    Direction(String commande) {
        this.commande = commande
    }

    void déplace(Ascenseur ascenseur) {

    }
}