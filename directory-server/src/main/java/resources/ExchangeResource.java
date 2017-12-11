package resources;

import api.ExchangeInfo;
import api.StringList;
import core.Company;
import core.Exchange;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/exchange")
@Produces(MediaType.APPLICATION_JSON)
public class ExchangeResource {
    private final HashMap<String, Exchange> exchanges;

    public ExchangeResource(HashMap<String, Exchange> exchanges) {

        this.exchanges = exchanges;

    }

    @Path("/{id}")
    @GET
    public ExchangeInfo getExchange(@PathParam("id") String id) {
        Exchange value = exchanges.get(id);

        return new ExchangeInfo(value);

    }

    @Path("/{id}/companies")
    @GET
    public StringList getCompanies(@PathParam("id") String id) {
        final Company[] value = exchanges.get(id).getCompanies();
        final String[] s = new String[value.length];
        int i = 0;
        for (Company c: value)
            s[i++] = c.getId();

        return new StringList(s);

    }

}
