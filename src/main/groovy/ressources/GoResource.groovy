package ressources

import metier.ActorAscenceur
import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class GoResource extends ServerResource {

    @Get
    public void représente() {
        def étage = getQueryValue("floorToGo") as int
        ActorAscenceur.actor << new Expando(action: "GO", étage: étage)
    }
}
