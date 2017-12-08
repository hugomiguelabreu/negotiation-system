package core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Company {

    private String id;
    private String name;
    private Exchange exchange;
    private PriceInfo today, yesterday;

    public Company(String id, String name, Exchange exchange){
        this.id = id;
        this.name = name;
        this.exchange = exchange;

        // For testing sake
        this.today = new PriceInfo(10, 10, 10, 10);
        this.yesterday = new PriceInfo(12, 15, 15.7, 17.8);
    }

    @JsonProperty
    public String getName(){
        return this.name;
    }

    @JsonProperty
    public String getId(){
        return this.id;
    }

    @JsonIgnore
    public Exchange getExchange() {
        return exchange;
    }

    @JsonIgnore
    public PriceInfo getToday(){
        return this.today;
    }

    @JsonIgnore
    public PriceInfo getYesterday() {
        return yesterday;
    }

    public class PriceInfo{

        private double max;
        private double min;
        private double open;
        private double close;

        public PriceInfo(double max, double min, double open, double close){
            this.max = max;
            this.min = min;
            this.open = open;
            this.close = close;
        }

        public double getMax() {
            return max;
        }

        public double getMin() {
            return min;
        }

        public double getClose() {
            return close;
        }

        public double getOpen() {
            return open;
        }
    }
}
