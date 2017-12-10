package core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class Exchange {

    private HashMap<String, Company> companies;
    private String name;
    private String addr;
    private String city;

    public Exchange(String name, String city, String address, String port){
        this.name = name;
        this.city = city;
        this.companies = new HashMap<>();
        this.addr = address + ":" + port;
    }

    public boolean add(Company c){

        if(!this.companies.containsKey(c.getName()))
            this.companies.put(c.getName(), c);
        else
            return false;

        return true;

    }

    @JsonIgnore
    public Company[] getCompanies(){

        return this.companies.values().toArray(new Company[0]);

    }

    public boolean remove(String name){

        if(!this.companies.containsKey(name))
            return false;

        this.companies.remove(name);
        return true;

    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getCity(){
        return this.city;
    }

    @JsonProperty
    public String getAddr(){
        return this.addr;
    }

}
