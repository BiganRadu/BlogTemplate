package org.example.blogtemplate.DAO;

import org.example.blogtemplate.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
