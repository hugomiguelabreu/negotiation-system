package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import core.Company;
import core.PriceInfo;

public class PriceInfoApi {

    private PriceInfo price;

    public PriceInfoApi() {
        // Jackson deserialization
    }

    public PriceInfoApi(PriceInfo price) {
        this.price = price;
    }

    @JsonValue
    public PriceInfo priceInfo() {
        return this.price;
    }
}
