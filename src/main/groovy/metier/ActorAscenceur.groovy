package metier

import groovyx.gpars.actor.DefaultActor

public class ActorAscenceur {

    public static final AscenceurActor actor = new AscenceurActor()


    public static class AscenceurActor extends DefaultActor {

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
}