package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import core.Company;

public class CompaniesList {

    private Company[] list;

    public CompaniesList() {
        // Jackson deserialization
    }

    public CompaniesList(Company[] list) {
        this.list = list;
    }

    @JsonValue
    public Company[] getCompanies() {
        return list;
    }
}
