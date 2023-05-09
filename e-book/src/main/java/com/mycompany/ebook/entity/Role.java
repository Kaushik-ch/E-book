package com.mycompany.ebook.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ROLE")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private String role;

    @Column(name = "created_dtm")
    private LocalDateTime createdDTM;

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDTM() {
        return createdDTM;
    }

    public void setCreatedDTM(LocalDateTime createdDTM) {
        this.createdDTM = createdDTM;
    }
}
