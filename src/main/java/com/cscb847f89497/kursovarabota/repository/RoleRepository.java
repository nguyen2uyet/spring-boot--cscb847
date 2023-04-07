package com.cscb847f89497.kursovarabota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cscb847f89497.kursovarabota.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
