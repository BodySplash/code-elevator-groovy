package ressources

import metier.ActorAscenceur
import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class ResetResource extends ServerResource{

    @Get
    public void représente() {
        ActorAscenceur.actor << new Expando(action : "RESET")
    }

}
