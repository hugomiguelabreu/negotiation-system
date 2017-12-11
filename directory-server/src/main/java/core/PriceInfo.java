package core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class PriceInfo{

    private double max;
    private double min;
    private double open;
    private double close;
    private Timestamp timestamp;

    @JsonCreator
    public PriceInfo(@JsonProperty("max") double max, @JsonProperty("min") double min,
                     @JsonProperty("open") double open, @JsonProperty("close") double close){
        this.max = max;
        this.min = min;
        this.open = open;
        this.close = close;
        this.timestamp = new Timestamp(System.currentTimeMillis());

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
    public String getTimestamp() {
        return this.timestamp.toString();
    }

    @JsonProperty("close")
    public void setClose(double close) {
        this.close = close;
    }

    @JsonProperty("max")
    public void setMax(double max) {
        this.max = max;
    }

    @JsonProperty("min")
    public void setMin(double min) {
        this.min = min;
    }

    @JsonProperty("open")
    public void setOpen(double open) {
        this.open = open;
    }
}