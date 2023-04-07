package com.cscb847f89497.kursovarabota.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import com.cscb847f89497.kursovarabota.dto.StudentDTO;
import com.cscb847f89497.kursovarabota.entity.Student;
import com.cscb847f89497.kursovarabota.repository.StudentRepository;
import com.cscb847f89497.kursovarabota.service.StudentService;
import com.cscb847f89497.kursovarabota.utils.Utils;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDTO findOneById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + id));
        ModelMapper mapper = new ModelMapper();
        StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    @Override
    public StudentDTO findOneByUsername(String username) {
        Student student = studentRepository.findOneByUsername(username);
        ModelMapper mapper = new ModelMapper();
        StudentDTO studentDTO = mapper.map(student, StudentDTO.class);
        return studentDTO;
    }

    @Override
    public void add(StudentDTO studentDTO) {
        ModelMapper mapper = new ModelMapper();
        studentRepository.save(mapper.map(studentDTO, Student.class));
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void update(StudentDTO studentDTO) {
        Student oldStudent = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Ivalid id: " + studentDTO.getId()));
        oldStudent = Utils.mapStudentDTOToStudent(studentDTO, oldStudent);
        studentRepository.save(oldStudent);
    }

    @Override
    public List<StudentDTO> list() {
        return Utils.mapList(studentRepository.findAll(), StudentDTO.class);
    }

}
