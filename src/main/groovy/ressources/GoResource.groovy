package ressources

import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class GoResource extends ServerResource {

    @Get
    public void représente() {
        def étage = getQueryValue("floorToGo") as int
        context.attributes['actor'] << new Expando(action: "GO", étage: étage)
    }
}
