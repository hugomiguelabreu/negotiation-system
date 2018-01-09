package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Company;

public class CompaniesList {

    private Company[] list;

    public CompaniesList() {
        // Jackson deserialization
    }

    public CompaniesList(Company[] list) {
        this.list = list;
    }

    @JsonProperty
    public Company[] getCompanies() {
        return list;
    }
}
