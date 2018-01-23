package data;

import rest.RESTClient;
import rest.core.PriceInfo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class PriceStats {

    private RESTClient rest;
    private String company;
    private double min;
    private double max;
    private double open;
    private double close;

    public PriceStats(String company, RESTClient rest) {
        //Inicia o timer para fecho de mercado
        this.closeTimer();
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

        //Pode ser a última transação;
        this.close = value;

        if (m || o || mx) {
            System.out.println("Value changes detected! Sending to REST Server.");
            rest.setPrice(company, new PriceInfo(max, min, open, 0));
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

    private void updateClose(){
        rest.setPrice(company, new PriceInfo(max, min, open, close));
    }

    private void closeTimer(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() - System.currentTimeMillis() < 0)
            calendar.add(Calendar.DAY_OF_MONTH, 1);

        Date alarmTime = calendar.getTime();
        //Now create the time and schedule it
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateClose();
            }
        }, (calendar.getTimeInMillis() - System.currentTimeMillis()), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }
}
