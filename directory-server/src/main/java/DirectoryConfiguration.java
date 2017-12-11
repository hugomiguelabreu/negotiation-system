import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class DirectoryConfiguration extends Configuration {

    @NotEmpty
    private String port = "8080";

    @JsonProperty
    public String getPort() {
        return this.port;
    }

    @JsonProperty
    public void setPort(String port) {
        this.port = port;
    }

}