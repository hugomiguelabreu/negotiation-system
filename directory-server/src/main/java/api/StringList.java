package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class StringList {

    private String[] list;

    public StringList() {
        // Jackson deserialization
    }

    public StringList(String[] list) {
        this.list = list;
    }

    @JsonProperty
    public String[] getList() {
        return list;
    }
}
