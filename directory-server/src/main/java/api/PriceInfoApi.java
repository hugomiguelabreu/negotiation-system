package api;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty
    public PriceInfo priceInfo() {
        return this.price;
    }
}
