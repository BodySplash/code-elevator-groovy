package ressources

import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class ResetResource extends ServerResource{

    @Get
    public void représente() {
        context.attributes['actor'] << new Expando(action : "RESET")
    }

}
