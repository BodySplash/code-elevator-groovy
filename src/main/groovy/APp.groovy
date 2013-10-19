import commande.AscenseurActor

import static org.ratpackframework.groovy.RatpackScript.ratpack

def actor = new AscenseurActor()
actor.start()
ratpack {
    handlers {

        get {
            response.send()
        }
        get('reset') {
            actor << [action: 'RESET']
            response.send()
        }
        get('nextCommand') {
            response.send(actor.sendAndPromise([action: 'NEXT']).get())
        }
        get('call') {
            actor << [
                    action : 'CALL',
                    étage : request.queryParams['atFloor'] as int,
                    direction : request.queryParams['to']
            ]
            response.send()
        }
        get('go') {
            actor << [action : 'GO',
                    étage : request.queryParams['floorToGo'] as int]
            response.send()
        }

        get('userHasEntered') {
            response.send()
        }

        get('userHasExited') {
            response.send()
        }

    }
}