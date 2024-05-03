package com.codegym.springsecurity.bai15_springsecurity_jwt.repository;

import com.codegym.springsecurity.bai15_springsecurity_jwt.model.dto.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
}
