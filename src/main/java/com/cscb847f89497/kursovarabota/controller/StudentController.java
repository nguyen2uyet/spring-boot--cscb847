package com.cscb847f89497.kursovarabota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cscb847f89497.kursovarabota.dto.StudentDTO;
import com.cscb847f89497.kursovarabota.security.MyUserDetails;
import com.cscb847f89497.kursovarabota.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    private String students(Model model) {
        model.addAttribute("students", studentService.list());
        return "student/students";
    }

    @GetMapping("/add/student")
    private String showFromAddStudent(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "student/new-student";
    }

    @PostMapping("/add/student")
    public String add(@ModelAttribute StudentDTO student, Model model) {
        studentService.add(student);
        return "redirect:/students";
    }

    @PostMapping("/update/student")
    public String update(@ModelAttribute StudentDTO student, Model model) {
        studentService.update(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/student/{id}")
    public String delete(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/student/{id}")
    public String edit(@PathVariable("id") String stdId, Model model) {
        Long id = Long.parseLong(stdId);
        model.addAttribute("student", studentService.findOneById(id));
        return "student/edit-student";
    }

    @GetMapping("/student/{username}")
    public String showStudent(@PathVariable("username") String username, Model model) {
        MyUserDetails principal = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUsername().equals(username)) {
            StudentDTO studentDTO = studentService.findOneByUsername(username);
            model.addAttribute("student", studentDTO);
        } else {
            return null;
        }
        return "/student/student";
    }

}
