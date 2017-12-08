package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Company;

public class PriceInfoApi {

    private Company.PriceInfo price;

    public PriceInfoApi() {
        // Jackson deserialization
    }

    public PriceInfoApi(Company.PriceInfo price) {
        this.price = price;
    }

    @JsonProperty("")
    public Company.PriceInfo priceInfo() {
        return this.price;
    }
}
