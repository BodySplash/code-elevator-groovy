package ressources

import metier.ActorAscenceur
import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class NextCommandResource extends ServerResource{
    @Get
    public String représente() {
        def promesse = ActorAscenceur.actor.sendAndPromise(new Expando(action: 'NEXT'))
        def resultat = promesse.get()
        println("######NEXT $resultat")
        return resultat
    }
}
