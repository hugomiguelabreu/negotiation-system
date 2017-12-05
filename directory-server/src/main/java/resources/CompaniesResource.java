package resources;

import api.Companies;
import core.Company;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompaniesResource {
    private final HashMap<String, Company> companies;

    public CompaniesResource(HashMap<String, Company> companies) {

        this.companies = companies;

    }

    @GET
    public Companies getList(@QueryParam("name") Optional<String> name) {
        final Company[] value = companies.values().toArray(new Company[0]);

        return new Companies(value);
    }
}
