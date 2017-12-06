package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class StringList {

    @Length(max = 2)
    private String[] companies;

    public StringList() {
        // Jackson deserialization
    }

    public StringList(String[] companies) {
        this.companies = companies;
    }

    @JsonProperty
    public String[] getCompanies() {
        return companies;
    }
}
