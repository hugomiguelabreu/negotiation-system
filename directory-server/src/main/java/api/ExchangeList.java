package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Exchange;
import org.hibernate.validator.constraints.Length;

public class ExchangeList {

    private Exchange[] list;

    public ExchangeList() {
        // Jackson deserialization
    }

    public ExchangeList(Exchange[] list) {
        this.list = list;
    }

    @JsonProperty
    public Exchange[] getExchanges() {
        return list;
    }
}
