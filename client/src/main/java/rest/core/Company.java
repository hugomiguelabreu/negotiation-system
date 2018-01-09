package rest.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

    private String id;
    private String name;
    private Exchange exchange;
    private PriceInfo today, yesterday;

    @JsonCreator
    public Company(@JsonProperty("id") String id, @JsonProperty("name") String name,
                   @JsonProperty("exchange") Exchange exchange, @JsonProperty("today") PriceInfo today, @JsonProperty("yesterday") PriceInfo yesterday){
        this.id = id;
        this.name = name;
        this.exchange = exchange;
        this.today = today;
        this.yesterday = yesterday;
    }

    @JsonProperty
    public String getName(){
        return this.name;
    }

    @JsonProperty
    public String getId(){
        return this.id;
    }

    @JsonProperty
    public Exchange getExchange() {
        return exchange;
    }

    @JsonProperty
    public PriceInfo getToday(){ return this.today; }

    @JsonProperty
    public PriceInfo getYesterday() { return this.yesterday; }


}
