package core;

public class Company {

    private String name;
    private Exchange exchange;
    private PriceInfo today, yesterday;

    public Company(String name, Exchange exchange){
        this.name = name;
        this.exchange = exchange;
    }

    public String getName(){
        return this.name;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public class PriceInfo{

        public PriceInfo(){

        }


    }
}
