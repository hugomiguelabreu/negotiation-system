package resources;

import api.CompaniesList;
import core.Company;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {
    private final HashMap<String, Company> companies;

    public CompaniesResource(HashMap<String, Company> companies) {

        this.companies = companies;

    }

    @GET
    public CompaniesList getList() {
        final Company[] value = companies.values().toArray(new Company[0]);

        return new CompaniesList(value);
    }

}
