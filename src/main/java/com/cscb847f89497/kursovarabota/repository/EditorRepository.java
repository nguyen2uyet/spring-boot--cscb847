package com.cscb847f89497.kursovarabota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cscb847f89497.kursovarabota.entity.Editor;

public interface EditorRepository extends JpaRepository<Editor, Long> {

    Editor findOneByName(String name);

    Editor findOneByUsername(String username);
}
