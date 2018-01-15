package rest.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceInfo {

    private double max;
    private double min;
    private double open;
    private double close;
    private String timestamp;

    @JsonCreator
    public PriceInfo(@JsonProperty("max") double max, @JsonProperty("min") double min,
                     @JsonProperty("open") double open, @JsonProperty("close") double close, @JsonProperty("timestamp") String timestamp){
        this.max = max;
        this.min = min;
        this.open = open;
        this.close = close;
        this.timestamp = timestamp;

    }

    public PriceInfo(double max, double min,
                     double open, double close){
        this.max = max;
        this.min = min;
        this.open = open;
        this.close = close;

    }

    @JsonProperty("max")
    public double getMax() {
        return max;
    }

    @JsonProperty("min")
    public double getMin() {
        return min;
    }

    @JsonProperty("close")
    public double getClose() {
        return close;
    }

    @JsonProperty("open")
    public double getOpen() {
        return open;
    }

    @JsonProperty("timestamp")
    @JsonIgnore
    public String getTimestamp() {
        return this.timestamp;
    }

}