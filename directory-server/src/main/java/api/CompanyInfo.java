package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public Company getCompany() {
        return this.company;
    }
}
