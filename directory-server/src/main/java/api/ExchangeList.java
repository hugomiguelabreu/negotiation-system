package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public Exchange[] getExchanges() {
        return list;
    }
}
