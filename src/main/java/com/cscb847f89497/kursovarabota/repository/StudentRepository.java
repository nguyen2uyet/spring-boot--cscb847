package com.cscb847f89497.kursovarabota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cscb847f89497.kursovarabota.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findOneByName(String name);

    Student findOneByUsername(String username);
}
