package com.mycompany.ebook.repository;


import com.mycompany.ebook.entity.Role;
import com.mycompany.ebook.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findUserRoleByUserEmail(String userEmail);
}
