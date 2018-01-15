package resources;

import api.CompanyInfo;
import api.PriceInfoApi;
import core.Company;
import core.PriceInfo;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.HashMap;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompanyResource {
    private final HashMap<String, Company> companies;

    public CompanyResource(HashMap<String, Company> companies) {

        this.companies = companies;

    }

    @Path("/{id}")
    @GET
    public CompanyInfo getInfo(@PathParam("id") String id){
        Company c = companies.get(id);

        return new CompanyInfo(c);
    }

    @Path("/{id}/today")
    @GET
    public PriceInfoApi getTodayInfo(@PathParam("id") String id){
        Company c = companies.get(id);

        return new PriceInfoApi(c.getToday());
    }

    @Path("/{id}/today")
    @PUT
    public Response putTodayInfo(@PathParam("id") String id, @Valid PriceInfo price){
        Company c = companies.get(id);

        c.updatePrice(price);

        return Response.created(UriBuilder.fromResource(CompanyResource.class)
                .build(c.getId(), id))
                .build();
    }

    @Path("/{id}/yesterday")
    @GET
    public PriceInfoApi getYesterdayInfo(@PathParam("id") String id){
        Company c = companies.get(id);

        return new PriceInfoApi(c.getYesterday());
    }
}
