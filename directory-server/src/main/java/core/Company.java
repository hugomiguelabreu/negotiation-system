package core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;

public class Company {

    private String id;
    private String name;
    private Exchange exchange;
    private PriceInfo today, yesterday;

    public Company(String id, String name, Exchange exchange){
        this.id = id;
        this.name = name;
        this.exchange = exchange;

        this.today = new PriceInfo(0, 0, 0, 0);
        this.yesterday = new PriceInfo(0, 0, 0, 0);
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

    public void updatePrice(PriceInfo price){
        synchronized (this) {
            this.today = price;
        }
    }

    @JsonProperty
    public PriceInfo getToday(){ return this.today; }

    @JsonProperty
    public PriceInfo getYesterday() { return this.yesterday; }

    public void changeDay(){
        synchronized (this){
            this.yesterday = this.today;
            this.today = new PriceInfo(0, 0, 0, 0);
        }
    }
}
