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

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        synchronized (this) {
            //Check if we need to change today to yesterday.
            if (Timestamp.valueOf(this.today.getTimestamp()).toLocalDateTime().getDayOfMonth() < cal.get(Calendar.DAY_OF_MONTH)) {
                this.yesterday = this.today;
                this.today = price;
            } else {
                this.today = price;
            }
        }
    }

    @JsonProperty
    public PriceInfo getToday(){

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        //Check if we need to change today to yesterday.
        if(Timestamp.valueOf(this.today.getTimestamp()).toLocalDateTime().getDayOfMonth() < cal.get(Calendar.DAY_OF_MONTH)){
            this.yesterday = this.today;
            this.today = new PriceInfo(0, 0, 0, 0);
        }

        return this.today;
    }

    @JsonProperty
    public PriceInfo getYesterday() {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        if(Timestamp.valueOf(this.today.getTimestamp()).toLocalDateTime().getDayOfMonth() < cal.get(Calendar.DAY_OF_MONTH)) {
            this.yesterday = this.today;
            this.today = new PriceInfo(0, 0, 0, 0);
        }

        return this.yesterday;

    }


}
