package metier


public enum Direction {

    HAUT("UP") {

        void deplace(Ascenseur ascenseur) {
            ascenseur.monte()
        }

        @Override
        int comparateur(etage, autre) {
            return etage.compareTo(autre)
        }

    }, BAS("DOWN") {

        void deplace(Ascenseur ascenseur) {
            ascenseur.descend()
        }

        @Override
        int comparateur(etage, autre) {
            autre.compareTo(etage)
        }
    };

    final String commande

    Direction(String commande) {
        this.commande = commande
    }

    static def parse(def direction) {
        if(direction == "UP")
            return HAUT
        return BAS
    }

    static def entre(def depart, def cible) {
        depart < cible ? HAUT : BAS
    }

    void deplace(Ascenseur ascenseur) {

    }

    int comparateur(def etage, def autre) {

    }
}