import core.Company;
import core.Exchange;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.CompaniesResource;

import java.util.HashMap;

public class Directory extends Application<DirectoryConfiguration> {

    private HashMap<String, Exchange> exchanges;
    private HashMap<String, Company> companies;


    public static void main(String[] args) throws Exception {
        new Directory().run(args);
    }

    @Override
    public String getName() {
        return "Directory-server";
    }

    @Override
    public void initialize(Bootstrap<DirectoryConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(DirectoryConfiguration configuration,
                    Environment environment) {

        this.companies = new HashMap<>();
        this.exchanges = new HashMap<>();

        Exchange e = new Exchange("NASDAQ", "192.168.1.1", "3001");
        this.companies.put("ALPH", new Company("Google", e));
        this.companies.put("AMZ", new Company("Amazon", e));
        this.companies.put("MCS", new Company("Microsoft", e));
        this.exchanges.put("NASDAQ", e);

        final CompaniesResource resource = new CompaniesResource(
            companies
        );
        environment.jersey().register(resource);
    }

}