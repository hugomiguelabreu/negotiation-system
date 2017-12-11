import core.Company;
import core.Exchange;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.CompaniesResource;
import resources.CompanyResource;
import resources.ExchangeResource;

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
        this.companies = new HashMap<>();
        this.exchanges = new HashMap<>();


        Exchange e = new Exchange("NASDAQ", "New York", "192.168.1.1", "3001");
        Company c;
        this.exchanges.put("NASDAQ", e);
        this.exchanges.put("PSI20", new Exchange("PSI20", "Lisbon", "192.168.1.1", "3005"));

        this.companies.put("ALPH", c = new Company("ALPH", "Alphabet", e));
        e.add(c);
        this.companies.put("AMZ", c = new Company("AMZ", "Amazon", e));
        e.add(c);
        this.companies.put("MCS", c = new Company("MCS","Microsoft", e));
        e.add(c);


    }

    @Override
    public void run(DirectoryConfiguration configuration,
                    Environment environment) {

        final CompanyResource companyresource = new CompanyResource(
            companies
        );
        final CompaniesResource companiesresource = new CompaniesResource(
            companies
        );
        final ExchangeResource exchangeresource = new ExchangeResource(
            exchanges
        );

        environment.jersey().register(companyresource);
        environment.jersey().register(companiesresource);
        environment.jersey().register(exchangeresource);
    }

}