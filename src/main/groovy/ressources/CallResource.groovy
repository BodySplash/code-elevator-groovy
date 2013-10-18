package ressources

import org.restlet.resource.Get
import org.restlet.resource.ServerResource

class CallResource extends ServerResource {

    @Get
    public void représente() {
        def étage = this.getQueryValue("atFloor") as int
        def direction = getQueryValue("to")
        context.attributes['actor'] << new Expando(action: "CALL", étage: étage, direction: direction)
    }
}
