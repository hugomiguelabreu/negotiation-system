import core.Company;
import core.Exchange;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.CompaniesResource;
import resources.CompanyResource;
import resources.ExchangeResource;
import resources.ExchangesResource;

import java.util.*;
import java.util.concurrent.TimeUnit;

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
        Company c;


        Exchange e = new Exchange("NASDAQ", "New York", "localhost", "4001");
        Exchange eP = new Exchange("PSI20", "Lisbon, Portugal", "localhost", "4005");

        this.exchanges.put("NASDAQ", e);
        this.exchanges.put("PSI20", eP);

        this.companies.put("ALPH", c = new Company("ALPH", "Alphabet", e));
        e.add(c);
        this.companies.put("AMZ", c = new Company("AMZ", "Amazon", e));
        e.add(c);
        this.companies.put("MCS", c = new Company("MCS","Microsoft", e));
        e.add(c);

        this.companies.put("EDP", c = new Company("EDP", "Energias de Portugal", eP));
        eP.add(c);
        this.companies.put("GLP", c = new Company("GLP", "Galp", eP));
        eP.add(c);
        this.companies.put("TAP", c = new Company("TAP","Transportadora aérea Portuguesa", eP));
        eP.add(c);

        //Initialize a trigger to change the day at midnight
        this.closeTimer(companies);

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
        final ExchangesResource exchangesresource = new ExchangesResource(
                exchanges
        );

        environment.jersey().register(companyresource);
        environment.jersey().register(companiesresource);
        environment.jersey().register(exchangeresource);
        environment.jersey().register(exchangesresource);
    }

    private void closeTimer(HashMap<String, Company> hc){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);

        Date alarmTime = calendar.getTime();
        //Now create the time and schedule it
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Company c: hc.values())
                    c.changeDay();
            }
        }, (calendar.getTimeInMillis() - System.currentTimeMillis()), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

}