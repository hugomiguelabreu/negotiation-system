package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import core.Exchange;


public class ExchangeInfo {

    private Exchange exchange;

    public ExchangeInfo() {
        // Jackson deserialization
    }

    public ExchangeInfo(Exchange exchange) {
        this.exchange = exchange;
    }

    @JsonValue
    public Exchange getExchange() {
        return this.exchange;
    }

}
