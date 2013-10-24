import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import commande.AscenseurActor

def actor = new AscenseurActor()
actor.start()

def serveur = HttpServer.create(new InetSocketAddress(8181), 0)
serveur.setExecutor(null)

def close = { exchange ->
    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0)
    exchange.close()
}

serveur.with {

    createContext("/reset", {
        exchange ->
            actor << [action: 'RESET']
            close exchange
    } as HttpHandler)

    createContext("/nextCommand", {
        exchange ->
            def commande = actor.sendAndPromise([action: 'NEXT']).get()
            exchange.sendResponseHeaders(200, commande.length())
            exchange.getResponseBody().write commande.bytes
            exchange.getResponseBody().close()
            exchange.getResponseBody().flush()
            exchange.close()
    } as HttpHandler)

    createContext("/call", {
        exchange ->
            close exchange
            def query = exchange.getRequestURI().getQuery()
            map = parseQuery(query)
            actor << [action: 'CALL', étage: map['atFloor'] as int, direction: map['to']]
    } as HttpHandler)

    createContext("/go", {
        exchange ->
            close exchange
            def map = parseQuery(exchange.getRequestURI().query)
            actor << [action: 'GO', étage: map['floorToGo'] as int]
    } as HttpHandler)

    createContext('/userHasEntered', close as HttpHandler)
    createContext('/userHasExited', close as HttpHandler)
}
serveur.start()


def parseQuery(query) {
    def map = query.split('&').inject([:]) {
        map, kv ->
            def (key, value) = kv.split('=').toList();
            map[key] = value
            map
    }
    map
}



