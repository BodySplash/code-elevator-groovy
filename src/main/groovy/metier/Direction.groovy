package metier


public enum Direction {

    HAUT("UP") {

        void déplace(Ascenseur ascenseur) {
            ascenseur.monte()
        }

        @Override
        int comparateur(étage, autre) {
            return étage.compareTo(autre)
        }

    }, BAS("DOWN") {

        void déplace(Ascenseur ascenseur) {
            ascenseur.descend()
        }

        @Override
        int comparateur(étage, autre) {
            autre.compareTo(étage)
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

    static def entre(def départ, def cible) {
        départ < cible ? HAUT : BAS
    }

    void déplace(Ascenseur ascenseur) {

    }

    int comparateur(def étage, def autre) {

    }
}