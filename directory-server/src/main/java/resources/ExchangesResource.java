package resources;

import api.StringList;
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
    public StringList getList() {
        final Exchange[] value = exchange.values().toArray(new Exchange[0]);
        final String[] s = new String[value.length];
        int i = 0;
        for (Exchange c: value)
            s[i++] = c.getName();

        return new StringList(s);
    }

}