package rest;

import rest.core.Company;
import rest.core.Exchange;
import rest.core.PriceInfo;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.HashMap;

public class RESTClient {

    HashMap<String, Company> cache;
    Client client;
    WebTarget webTarget;
    Invocation.Builder invocationBuilder;
    Response response;

    public RESTClient(){
        client = ClientBuilder.newClient();
        cache = new HashMap<>();
    }

    public Company getCompany(String id){

        if(cache.containsKey(id))
            return cache.get(id);

        webTarget = client.target("http://localhost:8080").path("company/" + id);
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();

        return response.readEntity(Company.class);
    }

    public Company[] getCompanies(){
        webTarget = client.target("http://localhost:8080").path("companies");
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        Company[] cs = response.readEntity(Company[].class);

        for (Company aux: cs)
            cache.put(aux.getId(), aux);

        return cs;
    }

    public Exchange[] getExchanges(){
        webTarget = client.target("http://localhost:8080").path("exchanges");
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();

        return response.readEntity(Exchange[].class);
    }

    public Exchange getExchange(String id){
        webTarget = client.target("http://localhost:8080").path("exchange/" + id);
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();

        return response.readEntity(Exchange.class);
    }

    public PriceInfo getPrice(String company, int i){
        webTarget = client.target("http://localhost:8080").path("company/" + company + "/" + (i == 0 ? "today" : "yesterday"));
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();

        return response.readEntity(PriceInfo.class);
    }

    // 0 today && 1 yesterday
    public boolean setPrice(String company, PriceInfo price){
        webTarget = client.target("http://localhost:8080").path("company/" + company + "/today");
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.put(Entity.json(price));

        return (response.getStatus() == 201);
    }

}
