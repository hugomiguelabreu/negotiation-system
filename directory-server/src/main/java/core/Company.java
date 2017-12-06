package core;

public class Company {

    private String id;
    private String name;
    private Exchange exchange;
    private PriceInfo today, yesterday;

    public Company(String id, String name, Exchange exchange){
        this.id = id;
        this.name = name;
        this.exchange = exchange;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public class PriceInfo{

        public PriceInfo(){

        }


    }
}
