package rest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import rest.core.Company;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClient {

    Client client = ClientBuilder.newClient();
    WebTarget webTarget;
    Invocation.Builder invocationBuilder;
    Response response;

    public RESTClient(){

        webTarget = client.target("http://localhost:8080").path("company/MCS");
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();


        Company msc = response.readEntity(Company.class);
        System.out.println(msc.getName());
        System.out.println(msc.getExchange().getAddr());

        webTarget = client.target("http://localhost:8080").path("companies");
        invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();

        Company[] kek = response.readEntity(Company[].class);

        for(Company x: kek){
            System.out.println(x.getName());
        }
    }

}
