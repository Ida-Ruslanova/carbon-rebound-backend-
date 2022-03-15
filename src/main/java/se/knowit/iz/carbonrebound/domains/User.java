package se.knowit.iz.carbonrebound.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class User {

    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String userName;

    @JsonCreator

    public User(@JsonProperty String id,
                @JsonProperty String firstName,
                @JsonProperty String lastName,
                @JsonProperty String email,
                @JsonProperty String password,
                @JsonProperty String userName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
