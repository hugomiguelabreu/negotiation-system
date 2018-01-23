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

    public PriceStats(String company, RESTClient rest) {
        this.rest = rest;
        this.company = company;
        min = max = open = close = 0;
        PriceInfo pi = rest.getPrice(company, 0);
        min = pi.getMin();
        max = pi.getMax();
        open = pi.getOpen();
        close = pi.getClose();
    }

    public void checkValue(double value){

        boolean o = checkOpen(value);
        boolean m = checkMin(value);
        boolean mx = checkMax(value);

        if (m || o || mx) {
            System.out.println("Value changes detected! Sending to REST Server.");
            rest.setPrice(company, new PriceInfo(max, min, open, close));
        }
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
        }else if(this.min == 0){
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
