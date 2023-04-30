package com.mycompany.ebook.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Marks this class as an Entity
@Getter // Defines the getter methods of the member fields
@Setter // Defines the setter methods of the member fields
@ToString // Defines a meaningful toString implementation of this class
@NoArgsConstructor // Defines the default constructor
public class User {

    @Id // Marks the "id" field as the identifier of this entity
    @GeneratedValue(strategy = GenerationType.AUTO) // The "id" field is to be generated automatically
    private Long id;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

