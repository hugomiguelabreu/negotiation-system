package resources;

import api.CompanyInfo;
import api.PriceInfoApi;
import api.StringList;
import core.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/company")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {
    private final HashMap<String, Company> companies;

    public CompaniesResource(HashMap<String, Company> companies) {

        this.companies = companies;

    }

    @GET
    public StringList getList() {
        final Company[] value = companies.values().toArray(new Company[0]);
        final String[] s = new String[value.length];
        int i = 0;
        for (Company c: value)
            s[i++] = c.getId();

        return new StringList(s);
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

    @Path("/{id}/yesterday")
    @GET
    public PriceInfoApi getYesterdayInfo(@PathParam("id") String id){
        Company c = companies.get(id);

        return new PriceInfoApi(c.getYesterday());
    }
}
