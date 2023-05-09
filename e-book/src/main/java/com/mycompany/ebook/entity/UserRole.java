package com.mycompany.ebook.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_ROLE")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "created_dtm")
    private LocalDateTime createdDTM;

    public UserRole() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDTM() {
        return createdDTM;
    }

    public void setCreatedDTM(LocalDateTime createdDTM) {
        this.createdDTM = createdDTM;
    }
}
