package data;

import rest.RESTClient;
import rest.core.PriceInfo;

public class PriceStats {

    private RESTClient rest;
    private String company;
    private double min;
    private double max;
    private double open;
    private double close;

    public PriceStats(RESTClient rest) {
        this.rest = rest;
        min = max = open = close = 0;
    }

    public void checkValue(double value){
        if (checkOpen(value) || checkMin(value)|| checkMax(value))
            System.out.println("Value changes detected! Sending to REST Server.");
            //rest.setPrice(company, new PriceInfo(min,max,open,close));
    }

    private boolean checkMax(double value) {
        if (this.max < value) {
            this.max = value;
            return true;
        }
        return false;
    }

    private boolean checkMin(double value) {
        if (this.min > value){
            this.min = value;
            return true;
        }
        return false;
    }

    private boolean checkOpen(double value) {
        if (this.open == 0){
            this.open = value;
            return true;
        }
        return false;
    }
}
