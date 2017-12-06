package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Company;
import org.hibernate.validator.constraints.Length;

public class CompanyInfo {

    private Company company;

    public CompanyInfo() {
        // Jackson deserialization
    }

    public CompanyInfo(Company company) {
        this.company = company;
    }

    @JsonProperty
    public Company getCompany() {
        return this.company;
    }
}
