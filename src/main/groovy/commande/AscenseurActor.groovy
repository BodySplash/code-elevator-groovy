package commande

import groovyx.gpars.actor.DefaultActor
import metier.Ascenseur
import metier.Direction

class AscenseurActor extends DefaultActor {

    private final def actions = [
            NEXT: { a, it ->
                reply a.prochaineCommande()
            },
            CALL: { a, it ->
                a.appelle(it.étage, Direction.parse(it.direction))
            },
            GO: { a, it ->
                a.go(it.étage)
            },
            RESET : { a, it ->
                a.reset()
            }]

    @Override
    protected void act() {
        def ascenceur = new Ascenseur()
        loop {
            react {
                println it
                actions[it.action].call(ascenceur, it)
            }
        }
    }
}
