package core;

import java.util.HashMap;

public class Exchange {

    private HashMap<String, Company> companies;
    private String name;
    private String addr;

    public Exchange(String name, String address, String port){
        this.name = name;
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

    public Company get(String name){

        return this.companies.get(name);

    }

    public boolean remove(String name){

        if(!this.companies.containsKey(name))
            return false;

        this.companies.remove(name);
        return true;

    }

    public String getName() {
        return name;
    }

    public String getAddr(){
        return this.addr;
    }

}
