package ressources

import metier.ActorAscenceur
import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class CallResource extends ServerResource {

    @Get
    public void représente() {
        def étage = this.getQueryValue("atFloor") as int
        def direction = getQueryValue("to")
        ActorAscenceur.actor.send(new Expando(action: "CALL", étage: étage, direction: direction))
    }
}
