package com.cscb847f89497.kursovarabota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cscb847f89497.kursovarabota.entity.Student;
import com.cscb847f89497.kursovarabota.entity.User;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByName(String name);

    Student findOneByUsername(String username);
}
