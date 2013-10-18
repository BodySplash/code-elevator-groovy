import commande.AscenseurActor
import org.restlet.Application
import org.restlet.Component
import org.restlet.Context
import org.restlet.Restlet
import org.restlet.data.Protocol
import org.restlet.routing.Router
import ressources.*

def actor = new AscenseurActor()
def context = new Context()
context.attributes.put("actor", actor)
def application = new Application(context) {
    @Override
    Restlet createInboundRoot() {
        def router = new Router(getContext())
        router.attach("/reset", ResetResource)
        router.attach("/nextCommand", NextCommandResource)
        router.attach("/call", CallResource)
        router.attach("/go", GoResource)
        router.attach("/userHasEntered", UserHasEnteredResource)
        router.attach("/userHasExited", UserHasExitedResource)
        return router
    }
}

def component = new Component()
component.servers.add(Protocol.HTTP, 8181)
component.defaultHost.attach(application)
actor.start()
component.start()




