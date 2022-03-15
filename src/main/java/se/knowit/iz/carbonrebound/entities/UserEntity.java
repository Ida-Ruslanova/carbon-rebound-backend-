package se.knowit.iz.carbonrebound.entities;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name="user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class UserEntity {
    @Id String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String userName;
}
