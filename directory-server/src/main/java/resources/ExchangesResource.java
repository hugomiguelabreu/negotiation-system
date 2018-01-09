package resources;

import api.ExchangeList;
import core.Exchange;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/exchanges")
@Produces(MediaType.APPLICATION_JSON)
public class ExchangesResource {
    private final HashMap<String, Exchange> exchange;

    public ExchangesResource(HashMap<String, Exchange> exchange) {

        this.exchange = exchange;

    }

    @GET
    public ExchangeList getList() {
        final Exchange[] value = exchange.values().toArray(new Exchange[0]);

        return new ExchangeList(value);
    }

}