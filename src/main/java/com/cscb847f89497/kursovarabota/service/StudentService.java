package com.cscb847f89497.kursovarabota.service;

import java.util.List;

import com.cscb847f89497.kursovarabota.dto.StudentDTO;

public interface StudentService {

    public StudentDTO findOneById(Long id);

    public List<StudentDTO> list();

    public void add(StudentDTO StudentDTO);

    public void delete(Long id);

    public void update(StudentDTO StudentDTO);

    public StudentDTO findOneByUsername(String username);

}
