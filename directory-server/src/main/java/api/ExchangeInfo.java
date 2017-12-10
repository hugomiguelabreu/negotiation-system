package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Exchange;


public class ExchangeInfo {

    private Exchange exchange;

    public ExchangeInfo() {
        // Jackson deserialization
    }

    public ExchangeInfo(Exchange exchange) {
        this.exchange = exchange;
    }

    @JsonProperty
    public Exchange getExchange() {
        return this.exchange;
    }

}
