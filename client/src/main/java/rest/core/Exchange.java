package rest.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Exchange {

    private String name;
    private String addr;
    private String city;

    @JsonCreator
    public Exchange(@JsonProperty("name") String name, @JsonProperty("city") String city, @JsonProperty("address") String address){
        this.name = name;
        this.city = city;
        this.addr = address;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getCity(){
        return this.city;
    }

    @JsonProperty
    public String getAddr(){
        return this.addr;
    }

}
