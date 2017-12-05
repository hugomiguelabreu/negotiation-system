package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import core.Company;
import org.hibernate.validator.constraints.Length;

public class Companies {

    @Length(max = 5)
    private Company[] content;

    public Companies() {
        // Jackson deserialization
    }

    public Companies(Company[] content) {
        this.content = content;
    }

    @JsonProperty
    public Company[] getContent() {
        return content;
    }
}
