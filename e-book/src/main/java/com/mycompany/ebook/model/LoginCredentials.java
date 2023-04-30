package com.mycompany.ebook.model;

import lombok.*;

@Getter // Defines the getter methods of the member fields
@Setter // Defines the setter methods of the member fields
@AllArgsConstructor // Defines a constructor that initializes all the member fields
@NoArgsConstructor // Defines no-args default constructor
@ToString // Defines a meaningful toString implementation of this class
public class LoginCredentials {

    private String email;
    private String password;

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
